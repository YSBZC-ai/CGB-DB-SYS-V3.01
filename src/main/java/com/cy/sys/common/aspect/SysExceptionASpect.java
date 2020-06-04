package com.cy.sys.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Auther: Administrator
 * @Date: 2020/3/30 09:04
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class SysExceptionASpect {

    /**
     * 通过如下方法进行异常信息的记录，但不会截断异常
     *
     * @param ex
     */
    @AfterThrowing(value = "bean(*ServiceImpl)", throwing = "ex")
    public void doLogError(JoinPoint joinPoint,Exception ex) throws NoSuchMethodException {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        //String methodName = ms.getDeclaringTypeName()+"."+ms.getName();
        Class<?> aClass = joinPoint.getTarget().getClass();
        //aClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        String methodName = aClass.getName()+"."+ms.getName();
        log.error("exception {} SysExceptionAspect {} ", ex.getMessage(),methodName);
    }
}
