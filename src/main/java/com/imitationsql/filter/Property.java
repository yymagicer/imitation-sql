package com.imitationsql.filter;

import java.io.Serializable;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:18
 */
@FunctionalInterface
public interface Property<T, R> extends Serializable {

    /**
     * @param t
     * @return
     */
    R apply(T t);
}
