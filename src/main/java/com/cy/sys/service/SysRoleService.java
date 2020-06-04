package com.cy.sys.service;

import com.cy.sys.common.vo.CheckBox;
import com.cy.sys.common.vo.PageObject;
import com.cy.sys.entity.SysRole;
import com.cy.sys.entity.SysRoleMenuVo;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/24 20:06
 * @Description:
 */
public interface SysRoleService {
    List<CheckBox> findObjects();

    int updateObject(SysRole entity,Long[] menuIds);

    SysRoleMenuVo findObjectById(Long id);

    int saveObject(SysRole entity,Long[]menuIds);

    int deleteObject(Long id);

    PageObject<SysRole> roleFindPageObjects(String name, Long pageCurrent);
}
