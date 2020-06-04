package com.cy.sys.dao;

import com.cy.sys.common.vo.Node;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.entity.SysUserMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 18:12
 * @Description:
 */
@Mapper
public interface SysMenuDao {

    int updateObject(SysMenu entity);

    int insertObject(SysMenu entity);

    @Select("select id,name,parentId from sys_menus")
    List<Node> findZtreeMenuNodes();

    List<Map<String, Object>> findObjects();

    /**
     * 基于菜单统计子菜单的个数
     * @param id 菜单id
     * @return 子菜单的个数
     */
    int getChildCount(Long id);

    /**
     * 基于菜单id删除菜单自身信息
     * @param id
     * @return
     */
    int deleteObject(Long id);

    List<String> findPermissions(@Param("menuIds") Integer[] menuIds);

    /**
     * 基于菜单获取菜单信息
     * @param menuIds
     * @return
     */
    List<SysUserMenuVo> findMenusByIds(@Param("menuIds")List<Integer> menuIds);

}
