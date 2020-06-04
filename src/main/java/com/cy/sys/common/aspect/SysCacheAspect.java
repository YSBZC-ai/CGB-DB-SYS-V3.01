package com.cy.sys.common.aspect;

import com.cy.sys.common.aspect.annotation.ClearCache;
import com.cy.sys.common.aspect.annotation.RequiredCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Administrator
 * @Date: 2020/3/30 10:16
 * @Description:
 */
@Component
@Aspect
public class SysCacheAspect {
    private Map<Object, Object> cache = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.cy.sys.common.aspect.annotation.RequiredCache)")
    public void doCache() {
    }

    @Pointcut("@annotation(com.cy.sys.common.aspect.annotation.ClearCache)")
    public void doClear() {
    }

    @AfterReturning("doClear()")
    public void doAfterReturning(JoinPoint joinPoint) throws NoSuchMethodException {
        Object key = null;
        Class<?> targetClass = joinPoint.getTarget().getClass();
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        System.out.println("targetMethod = " + targetMethod);
        ClearCache clearCache = targetMethod.getAnnotation(ClearCache.class);
        key = clearCache.Key();
        System.out.println("requiredCache.key = " + key);
        cache.remove(key);
    }

    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object key = null;
        Class<?> targetClass = joinPoint.getTarget().getClass();
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        /*Method targetMethod = ms.getMethod();//代理方式不同获取的结果也不同*/
        System.out.println("targetMethod = " + targetMethod);
        RequiredCache requiredCache = targetMethod.getAnnotation(RequiredCache.class);
        key = requiredCache.key();
        System.out.println("requiredCache.key = " + key);
        //======================================================
        System.out.println("Get Data from Cache");
        Object result = cache.get(key);
        if (result != null) return result;
        result = joinPoint.proceed();
        System.out.println("put Data to Cache");
        cache.put(key, result);
        return result;
    }
}
