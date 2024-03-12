package com.imitationsql.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: 表名 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 13:41
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {

    /**
     * 名称
     *
     * @return
     */
    String value();
}
