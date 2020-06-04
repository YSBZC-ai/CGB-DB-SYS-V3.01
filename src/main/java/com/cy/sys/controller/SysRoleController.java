package com.cy.sys.controller;

import com.cy.sys.common.vo.JsonResult;
import com.cy.sys.entity.SysRole;
import com.cy.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Administrator
 * @Date: 2020/3/24 20:15
 * @Description:
 */
@RestController
@RequestMapping("/role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("doFindRoles")
    public JsonResult doFindRoles() {
        return new JsonResult(sysRoleService.findObjects());
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysRole entity,Long[] menuIds) {
        sysRoleService.updateObject(entity, menuIds);
        return new JsonResult("update ok");
    }

    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Long id) {
        return new JsonResult(sysRoleService.findObjectById(id));
    }


    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysRole sysRole, Long[] menuIds) {
        sysRoleService.saveObject(sysRole, menuIds);
        return new JsonResult("Save ok");
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Long id) {
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String name, Long pageCurrent) {
        return new JsonResult(sysRoleService.roleFindPageObjects(name, pageCurrent));
    }
}
