package com.cy.sys.controller;

import com.cy.sys.common.vo.JsonResult;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 19:42
 * @Description:
 */
@RestController //等同于@Controller和@ResponseBody
@RequestMapping("/menu/")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu sysMenu) {
        sysMenuService.updateObjecy(sysMenu);
        return new JsonResult("update ok");
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysMenu sysMenu) {
        sysMenuService.saveObject(sysMenu);
        return new JsonResult("save ok");
    }

    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
        return new JsonResult(
                sysMenuService.findZtreeMenuNodes());
    }

    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects() {
        return new JsonResult(sysMenuService.findObjects());
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Long id) {
        sysMenuService.deleteObject(id);
        return new JsonResult("delete ok");
    }

}
