package com.imitationsql.expression;

/**
 * <p>Description: sql表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 11:30
 */

public interface SqlExpression<T> {

    /**
     * sql
     *
     * @return
     */
    String sql();
}
