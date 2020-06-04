package com.cy.sys.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 14:12
 * @Description:
 */
@Getter
@Setter
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -9207606648059280309L;
    /*状态码*/
    private int state = 1;//1表示success 0表示error
    /*状态信息*/
    private String message = "ok";
    /*正确数据*/
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String message) {
        this.message = message;
    }

    /*一般查询时调用，封装查询结果*/
    public JsonResult(Object data) {
        this.data = data;
    }

    /**
     * 出现异常时调用
     */
    public JsonResult(Throwable t) {
        this.state = 0;
        this.message = t.getMessage();
    }

}
