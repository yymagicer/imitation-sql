package com.imitationsql.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 18:24
 */
@Setter
@Getter
public class OrderByFilter<T> implements SqlFilter<T> {

    /**
     * 列名
     */
    private String columnName;
    /**
     * 正序
     */
    private boolean asc;

    public OrderByFilter(String columnName, boolean asc) {
        this.columnName = columnName;
        this.asc = asc;
    }
}
