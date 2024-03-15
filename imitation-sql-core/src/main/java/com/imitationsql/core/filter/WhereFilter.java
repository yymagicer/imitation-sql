package com.imitationsql.core.filter;

import com.imitationsql.core.enums.OperateEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:10
 */
@Setter
@Getter
public class WhereFilter<T> implements SqlFilter<T> {


    /**
     * 对象属性名称
     */
    private String propertyName;

    /**
     * 操作
     */
    private OperateEnum operateEnum;

    /**
     * 对象属性对应的值
     */
    private Object val;

    public WhereFilter(String propertyName, OperateEnum operateEnum, Object val) {
        this.propertyName = propertyName;
        this.operateEnum = operateEnum;
        this.val = val;
    }
}
