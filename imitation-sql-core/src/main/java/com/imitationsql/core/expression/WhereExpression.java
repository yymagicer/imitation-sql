package com.imitationsql.core.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.FilterNode;
import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.filter.Property;
import com.imitationsql.core.filter.WhereFilter;
import com.imitationsql.core.util.LambdaUtil;
import com.imitationsql.core.util.StringUtil;
import com.imitationsql.core.util.TypeConvertUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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
     * @param column Lambda 表达式
     * @param val    值
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
     * @param column      Lambda 表达式
     * @param operateEnum 操作类型
     * @param val         值
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(Property<T, P> column, OperateEnum operateEnum, V val) {
        return and(column, operateEnum, val, OperateEnum.AND);
    }


    /**
     * and查询
     *
     * @param tableName   表名
     * @param column      Lambda 表达式
     * @param operateEnum 操作类型
     * @param val         值
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String tableName, Property<T, P> column, OperateEnum operateEnum, V val) {
        return and(tableName, column, operateEnum, val, OperateEnum.AND);
    }

    /**
     * and 查询
     *
     * @param tableName 表名
     * @param column
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String tableName, Property<T, P> column, V val) {
        return and(tableName, column, OperateEnum.EQ, val, OperateEnum.AND);
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
     * @param columnName
     * @param operateEnum
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String columnName, OperateEnum operateEnum, V val) {
        return and(columnName, operateEnum, val, OperateEnum.AND);
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
        return and(null, column, operateEnum, val, expressionOperateEnum);
    }

    /**
     * and查询
     *
     * @param tableName
     * @param column
     * @param operateEnum
     * @param val
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> WhereExpression<T> and(String tableName, Property<?, P> column, OperateEnum operateEnum, V val, OperateEnum expressionOperateEnum) {
        String propertyName;
        if (StrUtil.isBlank(tableName)) {
            propertyName = LambdaUtil.getFullPropertyName(column);
        } else {
            propertyName = tableName + "." + LambdaUtil.getPropertyName(column);
        }
        if (this.filterNode == null) {
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(propertyName, operateEnum, val), expressionOperateEnum);
            this.filterNode = node;
            this.lastNode = node;
        } else {
            FilterNode<WhereFilter<T>, T> l = this.lastNode;
            FilterNode<WhereFilter<T>, T> node = new FilterNode<>(null, new WhereFilter<>(propertyName, operateEnum, val), expressionOperateEnum);
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
            if (filter.getOperateEnum().equals(OperateEnum.IN) || filter.getOperateEnum().equals(OperateEnum.NOT_IN)) {
                List<Object> list = (List<Object>) filter.getVal();
                builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(filter.getPropertyName()))).append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append("(").append(getString(list)).append(")");
            } else if (filter.getOperateEnum().equals(OperateEnum.LIKE)) {
                builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(filter.getPropertyName()))).append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append(StrUtil.wrap(filter.getVal().toString().replace("'", ""), "'%", "%'"));
            } else {
                builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(filter.getPropertyName()))).append(StringUtil.wrapBlank(filter.getOperateEnum().getType())).append(filter.getVal());
            }
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
                if (nextFilter.getOperateEnum().equals(OperateEnum.IN) || nextFilter.getOperateEnum().equals(OperateEnum.NOT_IN)) {
                    List<Object> list = (List<Object>) nextFilter.getVal();
                    builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(nextFilter.getPropertyName()))).append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append("(").append(getString(list)).append(")");
                } else if (nextFilter.getOperateEnum().equals(OperateEnum.LIKE)) {
                    builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(nextFilter.getPropertyName()))).append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append(StrUtil.wrap(nextFilter.getVal().toString().replace("'", ""), "'%", "%'"));
                } else {
                    builder.append(StringUtil.wrapBlank(StrUtil.toUnderlineCase(nextFilter.getPropertyName()))).append(StringUtil.wrapBlank(nextFilter.getOperateEnum().getType())).append(nextFilter.getVal());
                }
            }
            next = next.getNext();
        }
        return builder.toString();
    }

    private String getString(List<Object> list) {
        if (CollUtil.isEmpty(list)) {
            return "";
        }
        return list.stream().map(TypeConvertUtil::simpleConvert).collect(Collectors.joining(","));
    }
}
