package com.cloud.lx.aop;

import com.cloud.lx.service.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * redis注解类
 */
@Component
@Aspect
public class RedisAspect {

    @Autowired
    RedisService redisService;

    @Pointcut("@annotation(RedisAop)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object object = point.proceed();
        Object cache = saveToRedis(point, object);
        if (cache != null) {
            return cache;
        }
        return object;
    }

    public Object saveToRedis(ProceedingJoinPoint point, Object object) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        Parameter[] keys = method.getParameters();
        RedisAop redisAop = method.getAnnotation(RedisAop.class);
        String cacheKey = "";
        Object cacheValue = null;
        for (int i = 0; i < keys.length; i++) {
            String name = keys[i].getName();

            if (redisAop.value().equals(name)) {
                cacheValue = args[i];
            }
            if (redisAop.key().equals(name)) {
                cacheKey = (String) args[i];
            }
        }
        if (cacheValue == null) {
            cacheValue = object;
        }
        if (redisAop.mode() == 1) {
            if (!StringUtils.isEmpty(cacheKey)) {
                if (cacheValue != null) {
                    redisService.setString(redisAop.module() + cacheKey, cacheValue);
                }
            }
        } else if (redisAop.mode() == 2) {
            if (!StringUtils.isEmpty(cacheKey)) {
                redisService.delString(cacheKey);
            }
        } else if (redisAop.mode() == 3) {
            return redisService.getString(redisAop.module() + cacheKey);
        }
        return null;
    }
}
