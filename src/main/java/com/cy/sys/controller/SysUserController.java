package com.cy.sys.controller;

import com.cy.sys.common.vo.JsonResult;
import com.cy.sys.entity.SysUser;
import com.cy.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/3/27 16:24
 * @Description:
 */
@RequestMapping("/user/")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysUser entity, Long[] roleIds) {
        sysUserService.updateObject(entity, roleIds);
        return new JsonResult("update ok");
    }


    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Long id) {
        Map<String, Object> map = sysUserService.findObjectById(id);
        return new JsonResult(map);
    }


    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysUser entity, Long[] roleIds) {
        sysUserService.saveObject(entity, roleIds);
        return new JsonResult("save ok");
    }


    @RequestMapping("doValidById")
    public JsonResult doValidById(Long id, Integer valid) {
        sysUserService.validById(id, valid, "admin");//"admin"用户将来是登陆用户
        return new JsonResult("update ok");
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username, Long pageCurrent) {
        return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
    }

    @RequestMapping("doLogin")
    public JsonResult doLogin(String username, String password, boolean isRememberMe) {
        //1.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2.通过Subject提交用户信息,交给shiro框架进行认证操作
        //2.1对用户进行封装
        UsernamePasswordToken token = new UsernamePasswordToken(
                username,//身份信息
                password);//凭证信息
        if (isRememberMe) {
            token.setRememberMe(true);
        }
        //2.2对用户信息进行身份认证
        subject.login(token);
        //分析:
        //1)token会传给shiro的SecurityManager
        //2)SecurityManager将token传递给认证管理器
        //3)认证管理器会将token传递给realm
        return new JsonResult("login ok");
    }

    @ExceptionHandler(ShiroException.class)
    public JsonResult doHandleShiroException(ShiroException e) {
        JsonResult r = new JsonResult();
        r.setState(0);
        if (e instanceof UnknownAccountException) {
            r.setMessage("账户不存在");
        } else if (e instanceof LockedAccountException) {
            r.setMessage("账户已被禁用");
        } else if (e instanceof IncorrectCredentialsException) {
            r.setMessage("密码不正确");
        } else if (e instanceof AuthorizationException) {
            r.setMessage("没有此操作权限");
        } else {
            r.setMessage("系统维护中");
        }
        e.printStackTrace();
        return r;
    }

    @RequestMapping("doUpdatePassword")
    public JsonResult doUpdatePassword(String pwd, String newPwd, String cfgPwd) {
        sysUserService.updatePassword(pwd, newPwd, cfgPwd);
        return new JsonResult("update ok");
    }
}
