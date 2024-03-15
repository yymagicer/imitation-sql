package com.imitationsql.core.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 18:36
 */
@Setter
@Getter
public class GroupByFilter<T> implements SqlFilter<T> {
    /**
     * 列名
     */
    private String columnName;

    public GroupByFilter(String columnName) {
        this.columnName = columnName;
    }
}
