package com.cy.sys.dao;

import com.cy.sys.entity.SysUser;
import com.cy.sys.entity.SysUserDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/27 15:46
 * @Description:
 */
@Mapper
public interface SysUserDao {
    int updateObject(SysUser entity);

    SysUserDeptVo findObjectById(Long id);

    int insertObject(SysUser entity);

    @Update("update sys_users set valid=#{valid}, modifiedUser=#{modifiedUser}, modifiedTime=now() where id=#{id}")
    int validById(Long id, Integer valid, String modifiedUser);

    Long getRowCount( String username);

    List<SysUserDeptVo> findPageObjects(String username, Long startIndex, Integer pageSize);

    @Select("select * from sys_users where username = #{username}")
    SysUser findUserByUserName(String username);

    int updatePassword(@Param("password")String password, @Param("salt")String salt, @Param("id")Long id);

}
