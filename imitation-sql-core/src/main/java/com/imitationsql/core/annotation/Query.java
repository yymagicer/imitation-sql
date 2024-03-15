package com.imitationsql.core.annotation;

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
}
