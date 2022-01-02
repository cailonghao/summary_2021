package com.server.user.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class BackAspect {

    @Pointcut("@annotation(BackAop)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        checkSign(proceedingJoinPoint);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;

    }

    //部分打包会出现参数名为 var1  var2 的 ，需要指定参数顺序
    public void checkSign(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException, JsonProcessingException {
        Class<?> targetCls = proceedingJoinPoint.getTarget().getClass();
        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();
        Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        BackAop backAop = targetMethod.getAnnotation(BackAop.class);
        String sign = backAop.sign();
        Object[] values = proceedingJoinPoint.getArgs();
        Parameter[] names = targetMethod.getParameters();
        String realSign = "";
        String methodName = targetMethod.getName();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        Object yy = null;
        for (int i = 0; i < names.length; i++) {
            String par = names[i].getName();
            if (backAop.ignor().equals(par)) {
                continue;
            }
            map.put(par, values[i]);
            if (par.equals(sign)) {
                assert values[i] instanceof String;
                realSign = (String) values[i];
            }
        }
        String json = mapper.writeValueAsString(map);
        log.info("method = {},params={}", methodName, json);
//        Result result = getResult(realSign, backAop.level());
//        if (result.isFlag()) {
//            if (yy != null) {
//                BeanUtils.copyProperties(result.getData(), yy);
//            }
//        }
//        return result;
    }

}
