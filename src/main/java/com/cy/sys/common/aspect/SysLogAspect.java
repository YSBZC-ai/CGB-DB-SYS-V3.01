package com.cy.sys.common.aspect;

import com.cy.sys.common.aspect.annotation.RequiredLog;
import com.cy.sys.common.utils.IPUtils;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * @Auther: Administrator
 * @Date: 2020/3/28 15:30
 * @Description:
 * @Aspect:注解描述的类型为一个切面类型（aop中的横切面），这样的切面类型中通常会定义两部分内容 1切入点：切入扩展功能的点（例如业务对象中的一个或多个方法）
 * 2.通知：在切入点对应的方法执行时，要织入的扩展功能
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    /**
     * @Pointcut 注解一般用于描述方法，在方法上定义切入点。
     * bean(sysUserServiceImpl) 为一个切入点biao'da'shi
     */
    //@Pointcut("bean(sysUserServiceImpl)")
    @Pointcut("@annotation(com.cy.sys.common.aspect.annotation.RequiredLog)")
    public void doPointCut() {
    }//方法内部不需要写任何内容

    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("SysLogAspect.around");
        //记录方法执行时的开始时间
        long t1 = System.currentTimeMillis();
        try {
            //调用目标方法
            Object result = jp.proceed();//调用本切面中其它通知或下一个切面的通知或目标方法
            //记录方法执行的的结束时间以及总时长。
            long t2 = System.currentTimeMillis();
            log.info("method execute time {}", (t2 - t1));
            //将用户的正常行为信息写入到数据库
            saveLog(jp, t2 - t1);
            return result;
        } catch (Throwable e) {
            //出现异常时还要输出错误日志。
            log.error("error is {} ", e.getMessage());
            throw e;
        }
    }

    @Autowired
    private SysLogService sysLogService;

    private void saveLog(ProceedingJoinPoint point, long totleTime) throws Throwable {
        //1.获取日志信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        String className = targetClass.getName();
        //获取接口声明的方法
        String methodName = ms.getMethod().getName();
        Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
        //获取目标对象方法(AOP版本不同,可能获取方法对象方式也不同)
        Method targetMethod = targetClass.getDeclaredMethod(methodName, parameterTypes);
        //获取用户名,学完shiro再进行自定义实现,没有就先给固定值
        /*String username=ShiroUtils.getPrincipal().getUsername();*/
        //获取方法参数
        Object[] paramsObj = point.getArgs();
        System.out.println("paramsObj=" + paramsObj);
        //将参数转换为字符串
        String params = new ObjectMapper().writeValueAsString(paramsObj);
        //2.封装日志信息
        SysLog log = new SysLog();
        log.setUsername("admin");//登陆的用户
        //假如目标方法对象上有注解,我们获取注解定义的操作值
        RequiredLog requestLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
        String operation = "opreation";
        if (requestLog != null) operation = requestLog.operation();
        log.setOperation(operation);
        log.setMethod(className + "." + methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(totleTime);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
       /* new Thread() {
            @Override
            public void run() {
                sysLogService.saveObject(log);
            }
        }.start();*/
        Future<Integer> future = sysLogService.saveObject(log);
        /*Integer rows = future.get();*/
    }

}
