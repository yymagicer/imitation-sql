package com.imitationsql.core;

import com.imitationsql.core.enums.OperateEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: 列值关系 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 17:23
 */
@Setter
@Getter
public class ColumnValueRelation {


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
}
