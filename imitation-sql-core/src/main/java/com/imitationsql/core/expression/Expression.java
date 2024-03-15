package com.imitationsql.core.expression;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:05
 */
@FunctionalInterface
public interface Expression<T> {
    /**
     * @param t
     */
    void apply(T t);
}
