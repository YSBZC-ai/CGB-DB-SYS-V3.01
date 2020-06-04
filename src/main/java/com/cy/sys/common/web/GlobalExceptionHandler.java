package com.cy.sys.common.web;

import com.cy.sys.common.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 14:56
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //JDK中的自带的日志API
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(
            RuntimeException e) {
        e.printStackTrace();//也可以写日志
        return new JsonResult(e);//封装异常信息
    }
}
