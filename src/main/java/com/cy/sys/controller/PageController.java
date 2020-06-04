package com.cy.sys.controller;

import com.cy.sys.common.utils.ShiroUtils;
import com.cy.sys.entity.SysUser;
import com.cy.sys.entity.SysUserMenuVo;
import com.cy.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/18 15:05
 * @Description:
 */
@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("doIndexUI")
    public String doIndexUI(Model model) {
        SysUser user= ShiroUtils.getUser();
        model.addAttribute("user",user);
        Integer id = user.getId().intValue();
        List<SysUserMenuVo> userMenus = sysMenuService.findUserMenusByUserId(id);
        model.addAttribute("userMenus",userMenus);
        return "starter";
    }

    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }

    /*@RequestMapping("log/log_list")
    public String doLogUI() {
        return "sys/log_list";
    }*/

    /*@RequestMapping("menu/menu_list")
    public String doMenuUI() {
        return "sys/menu_list";
    }*/

    /**
     * REST风格的url映射：REST是一种软件架构的编码风格，在这种风格下的url定义，可以使用{变量}的方式让url更加简单通用。
     * 在方法参数中需要url中的{变量}值时，需要使用@PathVariable注解对方法参数使用描述，并且要求方法的参数和url的{变量}名保持一周
     *
     * @param moduleUI
     * @return
     */
    @RequestMapping("/{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI) {//@PathVariable 获取url里的moduleUI的值
        return "sys/" + moduleUI;
    }

    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }

}
