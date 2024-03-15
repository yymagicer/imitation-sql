package com.imitationsql.core.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.FilterNode;
import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.filter.Property;
import com.imitationsql.core.filter.WhereFilter;
import com.imitationsql.core.util.LambdaUtil;
import com.imitationsql.core.util.StringUtil;
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
        return and(column, OperateEnum.EQ, val, OperateEnum.AND);
    }

    /**
     * and查询
     *
     * @param columnName
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String columnName, V val) {
        return and(columnName, OperateEnum.EQ, val, OperateEnum.AND);
    }


    /**
     * and查询
     *
     * @return
     */
    public WhereExpression<T> and() {
        return and((Property<?, Object>) null, OperateEnum.AND, null, null);
    }


    /**
     * or查询
     *
     * @return
     */
    public WhereExpression<T> or() {
        return and((Property<?, Object>) null, OperateEnum.OR, null, null);
    }


    /**
     * and查询
     *
     * @return
     */
    public WhereExpression<T> end() {
        return and((Property<?, Object>) null, OperateEnum.END, null, null);
    }

    /**
     * eq
     *
     * @param column
     * @param val
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> eq(Property<T, P> column, V val) {
        return and(column, OperateEnum.EQ, val, null);
    }

    /**
     * eq
     *
     * @param column
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <T2, P, V> WhereExpression<T> eq(Class<T2> entityClass, Property<T2, P> column, V val) {
        return and(column, OperateEnum.EQ, val, OperateEnum.AND);
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
        return and(column, OperateEnum.EQ, val, OperateEnum.AND);
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
    public <P, V> WhereExpression<T> and(Property<?, P> column, OperateEnum operateEnum, V val, OperateEnum expressionOperateEnum) {
        if (this.filterNode == null) {
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(LambdaUtil.getFullPropertyName(column), operateEnum, val), expressionOperateEnum);
            this.filterNode = node;
            this.lastNode = node;
        } else {
            FilterNode<WhereFilter<T>, T> l = this.lastNode;
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(LambdaUtil.getFullPropertyName(column), operateEnum, val), expressionOperateEnum);
            l.setNext(node);
            this.lastNode = node;
        }
        return this;
    }


    /**
     * and查询
     *
     * @param operateEnum
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String columnName, OperateEnum operateEnum, V val, OperateEnum expressionOperateEnum) {
        if (this.filterNode == null) {
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(columnName, operateEnum, val), expressionOperateEnum);
            this.filterNode = node;
            this.lastNode = node;
        } else {
            FilterNode<WhereFilter<T>, T> l = this.lastNode;
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(columnName, operateEnum, val), expressionOperateEnum);
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
        if (null == filter.getPropertyName() && null == filter.getVal()) {
            if (!OperateEnum.END.equals(filter.getOperateEnum())) {
                builder.append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append("(");
            }
        } else {
            builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(filter.getPropertyName()))).append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append(filter.getVal());
        }
        FilterNode<WhereFilter<T>, T> next = this.filterNode.getNext();
        while (null != next) {
            WhereFilter<T> nextFilter = next.getFilter();
            if (null != next.getOperateEnum()) {
                builder.append(StringUtil.wrapBlank(next.getOperateEnum().getType()));
            }
            if (null == nextFilter.getPropertyName() && null == nextFilter.getVal()) {
                if (!OperateEnum.END.equals(nextFilter.getOperateEnum())) {
                    builder.append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append("(");
                } else {
                    builder.append(")");
                }
            } else {
                builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(nextFilter.getPropertyName()))).append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append(nextFilter.getVal());
            }
            next = next.getNext();
        }
        return builder.toString();
    }
}
