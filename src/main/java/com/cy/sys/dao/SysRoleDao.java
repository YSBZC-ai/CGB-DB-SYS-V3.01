package com.cy.sys.dao;

import com.cy.sys.common.vo.CheckBox;
import com.cy.sys.entity.SysRole;
import com.cy.sys.entity.SysRoleMenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/24 20:01
 * @Description:
 */
@Mapper
public interface SysRoleDao {
    @Select("select id,name from sys_roles")
    List<CheckBox> findObjects();

    @Update("update sys_roles set name=#{name},note=#{note},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int updateObject(SysRole entity);

    //基于角色id查询角色及角色对应的菜单信息
    SysRoleMenuVo findObjectById(Long id);

    //保存角色自身信息
    int insertObject(SysRole sysRole);

    //基于角色id删除
    @Delete("delete from sys_roles where id =#{id}")
    int deleteObject(Long id);

    int getRowCount(String name);

    List<SysRole> findPageObjects(String name,long startIndex,Integer pageSize);

}
