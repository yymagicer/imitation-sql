package com.imitationsql.core.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 15:19
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryKey {

    /**
     * 主键名称，默认id
     *
     * @return
     */
    String value() default "id";
}
