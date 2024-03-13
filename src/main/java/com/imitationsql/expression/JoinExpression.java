package com.imitationsql.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.enums.OperateEnum;
import com.imitationsql.filter.Property;
import com.imitationsql.util.LambdaUtil;

/**
 * <p>Description: join 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 9:26
 */
public abstract class JoinExpression<T> implements SqlExpression<T> {

    /**
     * join entity类
     */
    protected Class<?> joinEntityClass;

    /**
     * join entity类
     */
    protected Class<?> joinEntityClass2;

    /**
     * 列名
     */
    protected String columnName;

    /**
     * 操作名称，默认式eq
     */
    protected OperateEnum operateEnum = OperateEnum.EQ;

    /**
     * join 列名
     */
    protected String joinColumnName;

    /**
     * eq
     *
     * @param joinEntityClass join对象class
     * @param column          列名
     * @param joinColumn      join 列名
     * @param <T2>
     * @param <P>
     * @return
     */
    public <T2, P> JoinExpression<T> eq(Class<T2> joinEntityClass, Property<T, P> column, Property<T2, P> joinColumn) {
        this.joinEntityClass = joinEntityClass;
        this.columnName = StrUtil.toUnderlineCase(LambdaUtil.getFullPropertyName(column));
        this.joinColumnName = StrUtil.toUnderlineCase(LambdaUtil.getFullPropertyName(joinColumn));
        return this;
    }


    /**
     * @param joinEntityClass  join对象class
     * @param joinEntityClass2 join对象class2
     * @param column           列名
     * @param joinColumn       join 列名
     * @param <T2>
     * @param <T3>
     * @param <P>
     * @return
     */
    public <T2, T3, P> JoinExpression<T> eq(Class<T2> joinEntityClass, Class<T3> joinEntityClass2, Property<T2, P> column, Property<T3, P> joinColumn) {
        this.joinEntityClass = joinEntityClass;
        this.joinEntityClass2 = joinEntityClass2;
        this.columnName = StrUtil.toUnderlineCase(LambdaUtil.getFullPropertyName(column));
        this.joinColumnName = StrUtil.toUnderlineCase(LambdaUtil.getFullPropertyName(joinColumn));
        return this;
    }
}
