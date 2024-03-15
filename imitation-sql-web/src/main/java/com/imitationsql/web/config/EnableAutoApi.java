package com.imitationsql.web.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>Description: 自动生成api </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 10:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EnableAutoApi {

    String value() default "";
}
