package com.imitationsql.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.FilterNode;
import com.imitationsql.enums.OperateEnum;
import com.imitationsql.filter.Property;
import com.imitationsql.filter.WhereFilter;
import com.imitationsql.util.LambdaUtil;
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
        return and(column, val, OperateEnum.EQ);
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
    public <P, V> WhereExpression<T> and(Property<T, P> column, V val, OperateEnum operateEnum) {
        if (this.filterNode == null) {
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>();
            WhereFilter<T> whereFilter = new WhereFilter<>(LambdaUtil.getPropertyName(column), operateEnum, val);
            node.setFilter(whereFilter);
            this.filterNode = node;
            this.lastNode = node;
        } else {
            FilterNode<WhereFilter<T>, T> l = this.lastNode;
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>();
            WhereFilter<T> whereFilter = new WhereFilter<>(LambdaUtil.getPropertyName(column), operateEnum, val);
            node.setFilter(whereFilter);
            l.setNext(node);
            this.lastNode = node;
        }
        return this;
    }

    @Override
    public String sql() {
        StringBuilder builder = new StringBuilder(" where ");
        if (null == this.filterNode) {
            return "";
        }
        WhereFilter<T> filter = this.filterNode.getFilter();
        builder.append(StrUtil.toUnderlineCase(filter.getPropertyName())).append(filter.getOperateEnum().getType()).append(filter.getVal());
        FilterNode<WhereFilter<T>, T> next = this.filterNode.getNext();
        while (null != next) {
            builder.append(" and ");
            WhereFilter<T> nextFilter = next.getFilter();
            builder.append(StrUtil.toUnderlineCase(nextFilter.getPropertyName())).append(nextFilter.getOperateEnum().getType()).append(nextFilter.getVal());
            next = next.getNext();
        }
        return builder.toString();
    }
}
