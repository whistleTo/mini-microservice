package com.psbc.aop;

import com.psbc.annotation.Cost;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CostAspect {

    @Around("@annotation(cost)")
    public Object around(ProceedingJoinPoint joinPoint, Cost cost) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("方法执行耗时：{} ms → 方法全路径：{}", (end - start), methodName);

        return result;
    }
}