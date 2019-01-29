package com.cmcc.spring.Annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    //标识限制次数
    int limit() default 5;
    //标识时间段
    int sec() default 5;
}
