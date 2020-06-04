package com.cy.sys.controller;

import com.cy.sys.common.vo.JsonResult;
import com.cy.sys.common.vo.PageObject;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2020/3/18 17:16
 * @Description:
 */
@Controller
@RequestMapping("/log/")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(String username,Long pageCurrent) {
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects(username, pageCurrent);
        return new JsonResult(pageObjects);
    }

    @RequestMapping("doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Long...ids){
        sysLogService.deleteObject(ids);
        return new JsonResult("delete ok");
    }
}
