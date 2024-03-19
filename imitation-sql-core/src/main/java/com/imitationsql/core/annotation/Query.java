package com.imitationsql.core.annotation;

import com.imitationsql.core.enums.OperateEnum;

import java.lang.annotation.*;

/**
 * <p>Description: query注解 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 10:03
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Query {

    /**
     * 操作，默认 eq查询
     *
     * @return
     */
    OperateEnum operate() default OperateEnum.EQ;
}
