package com.server.user.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BackAop {

    /**
     * 接口的描述
     *
     * @return
     */
    String name();

    /**
     * 需要鉴权的字段
     *
     * @return
     */
    String sign() default "sign";

    /**
     * 需要忽略的参数
     *
     * @return
     */
    String ignor() default "request";

    /**
     * 操作等级 默认为3
     */
    int level() default 3;
}
