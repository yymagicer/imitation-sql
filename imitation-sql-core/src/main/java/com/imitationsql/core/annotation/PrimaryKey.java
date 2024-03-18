package com.imitationsql.core.annotation;

import com.imitationsql.core.enums.IdType;

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


    /**
     * 主键id类型，默认数据库ID自增
     *
     * @return
     */
    IdType type() default IdType.AUTO;


}
