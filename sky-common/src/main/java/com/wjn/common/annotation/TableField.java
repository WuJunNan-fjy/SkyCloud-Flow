package com.wjn.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的注解，模拟 MyBatis-Plus 的 @TableField 注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {
    String value() default "";  // 映射的数据库字段名
    boolean exist() default true;  // 是否映射到数据库字段
}
