package com.cy.sys.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: Administrator
 * @Date: 2020/3/30 08:48
 * @Description:
 */
@Order(2)
@Aspect
@Component
public class SysTimeAspect {
    @Pointcut("bean(sysUserServiceImpl)")
    public void doTime() {
    }

    @Before("doTime()")
    public void doBefore() {
        System.out.println("@Before");
    }

    @After("doTime()")
    public void doAfter() {
        System.out.println("@After");
    }

    /**
     * 核心业务正常结束时执行* 说明：假如有after，先执行after,再执行returning
     */
    @AfterReturning("doTime()")
    public void doAfterReturning() {
        System.out.println("@AfterReturning");
    }

    /**
     * 核心业务出现异常时执行说明：假如有after，先执行after,再执行Throwing
     */
    @AfterThrowing("doTime()")
    public void doAfterThrowing() {
        System.out.println("@AfterThrowing");
    }


    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("timeAround");
        System.out.println("around.before");
        try {
            Object o = jp.proceed();
            System.out.println("around.after");
            return o;
        } catch (Throwable throwable) {
            System.out.println("around.exception");
            throw throwable;
        }
    }
}
