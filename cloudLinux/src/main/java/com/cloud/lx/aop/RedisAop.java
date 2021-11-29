package com.cloud.lx.aop;

import java.lang.annotation.*;

/**
 * redis缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisAop {

    /**
     * key关键字
     */
    String key();

    /**
     * 模块 完整的key为 module-key
     *
     * @return
     */
    String module();

    /**
     * 1 添加 2 删除 3 查询
     */
    int mode() default 1;

    /**
     * value值
     */
    String value() default "";
}
