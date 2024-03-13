package com.imitationsql.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.FilterNode;
import com.imitationsql.enums.OperateEnum;
import com.imitationsql.enums.SqlKeyword;
import com.imitationsql.filter.Property;
import com.imitationsql.filter.WhereFilter;
import com.imitationsql.util.LambdaUtil;
import com.imitationsql.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: where 表达式  </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 14:14
 */
@Setter
@Getter
public class WhereExpression<T> implements SqlExpression<T> {

    private FilterNode<WhereFilter<T>, T> filterNode;

    private FilterNode<WhereFilter<T>, T> lastNode;

    /**
     * and查询
     *
     * @param column
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(Property<T, P> column, V val) {
        return and(column, OperateEnum.EQ, val);
    }

    /**
     * and查询
     *
     * @param column
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <T2, P, V> WhereExpression<T> and(Class<T2> entityClass, Property<T2, P> column, V val) {
        return and(column, OperateEnum.EQ, val);
    }


    /**
     * and查询
     *
     * @param column
     * @param operateEnum
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(Property<?, P> column, OperateEnum operateEnum, V val) {
        if (this.filterNode == null) {
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>();
            WhereFilter<T> whereFilter = new WhereFilter<>(LambdaUtil.getFullPropertyName(column), operateEnum, val);
            node.setFilter(whereFilter);
            this.filterNode = node;
            this.lastNode = node;
        } else {
            FilterNode<WhereFilter<T>, T> l = this.lastNode;
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>();
            WhereFilter<T> whereFilter = new WhereFilter<>(LambdaUtil.getFullPropertyName(column), operateEnum, val);
            node.setFilter(whereFilter);
            l.setNext(node);
            this.lastNode = node;
        }
        return this;
    }

    @Override
    public String sql() {
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.WHERE.getKeyword()));
        if (null == this.filterNode) {
            return "";
        }
        WhereFilter<T> filter = this.filterNode.getFilter();
        builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(filter.getPropertyName()))).append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append(filter.getVal());
        FilterNode<WhereFilter<T>, T> next = this.filterNode.getNext();
        while (null != next) {
            builder.append(StringUtil.wrapBlank(SqlKeyword.AND.getKeyword()));
            WhereFilter<T> nextFilter = next.getFilter();
            builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(nextFilter.getPropertyName()))).append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append(nextFilter.getVal());
            next = next.getNext();
        }
        return builder.toString();
    }
}
