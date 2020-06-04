package com.cy.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 20:30
 * @Description:
 */
@Mapper
public interface SysRoleMenuDao {
    int insertObjects(Long roleId,Long[] menuIds);

    //基于角色id删除关系数据的方法
    @Delete("delete from sys_role_menus where role_id = #{roleId}")
    int deleteObjectByRoleId(Long roleId);

    /**
     * 基于菜单id删除角色菜单关系数据
     * @param menuId
     * @return
     */
    int deleteObjectsByMenuId(Long menuId);

    List<Integer> findMenuIdsByRoleIds(@Param("roleIds")Long[] roleIds);

}
