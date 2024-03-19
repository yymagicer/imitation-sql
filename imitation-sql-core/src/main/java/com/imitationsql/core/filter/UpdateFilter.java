package com.imitationsql.core.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/18 17:46
 */
@Setter
@Getter
public class UpdateFilter<T> implements SqlFilter<T> {
    /**
     * 对象属性名称
     */
    private String propertyName;


    /**
     * 对象属性对应的值
     */
    private Object val;

    public UpdateFilter(String propertyName, Object val) {
        this.propertyName = propertyName;
        this.val = val;
    }
}
