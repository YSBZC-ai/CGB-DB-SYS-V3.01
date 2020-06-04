package com.cy.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/25 16:30
 * @Description:
 */
@Mapper
public interface SysUserRoleDao {
    int deleteObjectsByUserId(Long userId);

    List<Long> findRoleIdsByUserId(Long id);

    int insertObjects(Long userId, Long[] roleIds);


    @Delete("delete from sys_user_roles where role_id=#{roleId}")
    int deleteObjectsByRoleId(Long roleId);

}
