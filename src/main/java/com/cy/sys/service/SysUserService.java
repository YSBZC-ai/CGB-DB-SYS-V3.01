package com.cy.sys.service;

import com.cy.sys.common.vo.PageObject;
import com.cy.sys.entity.SysUser;
import com.cy.sys.entity.SysUserDeptVo;

import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/3/27 16:19
 * @Description:
 */
public interface SysUserService {
    int updateObject(SysUser entity, Long[] roleIds);

    Map<String, Object> findObjectById(Long userId);

    int saveObject(SysUser entity, Long[] roleIds);

    int validById(Long id, Integer valid, String modifiedUser);

    PageObject<SysUserDeptVo> findPageObjects(String username, Long pageCurrent);

    int updatePassword(String password, String newPassword, String cfgPassword);

}
