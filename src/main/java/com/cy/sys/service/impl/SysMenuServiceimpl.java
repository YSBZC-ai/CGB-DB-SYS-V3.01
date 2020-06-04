package com.cy.sys.service.impl;

import com.cy.sys.common.aspect.annotation.RequiredCache;
import com.cy.sys.common.aspect.annotation.ClearCache;
import com.cy.sys.common.exception.ServiceException;
import com.cy.sys.common.vo.Node;
import com.cy.sys.dao.SysMenuDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.entity.SysUserMenuVo;
import com.cy.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 19:32
 * @Description:
 */
@Service
public class SysMenuServiceimpl implements SysMenuService {
    @Autowired
    SysMenuDao sysMenuDao;
    @Autowired
    SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    SysUserRoleDao sysUserRoleDao;

    @ClearCache(Key = "menuData")
    @Override
    public int updateObjecy(SysMenu entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("菜单名不能为空");
        //2.更新数据
        int rows=sysMenuDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //3.返回数据
        return rows;
    }

    @ClearCache(Key = "menuData")
    @Override
    public int saveObject(SysMenu sysMenu) {
        if (sysMenu == null) {
            throw new IllegalArgumentException("保存对象不能为空");
        }
        int rows = sysMenuDao.insertObject(sysMenu);
        return rows;
    }

    @RequiredCache(key = "menuData")
    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> objects = sysMenuDao.findObjects();
        if (objects == null || objects.size() == 0) {
            throw new ServiceException("没有找到对应数据");
        }
        return objects;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @ClearCache(Key = "menuData")
    @Override
    public int deleteObject(Long id) {
        //1.验证数据的合法性
        if (id == null || id <= 0)
            throw new IllegalArgumentException("请先选择");
        //2.基于id进行子元素查询
        int count = sysMenuDao.getChildCount(id);
        if (count > 0) {
            throw new ServiceException("请先删除子菜单");
        }
        //3.删除角色,菜单关系数据
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        //4.删除菜单元素
        int rows = sysMenuDao.deleteObject(id);
        if (rows == 0)
            throw new ServiceException("此菜单可能已经不存在");
        //5.返回结果
        return rows;
    }

    @Override
    public List<SysUserMenuVo> findUserMenusByUserId(Integer id) {
        //1.对用户id进行判断
        //2.基于用户id查找用户对应的角色id
        List<Long> roleIds = sysUserRoleDao.findRoleIdsByUserId(Long.valueOf(id));
        //3.基于角色id获取角色对应的菜单信息,并进行封装.
        List<Integer> menuIds= sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(new Long[] {}));
        //4.基于菜单id获取用户对应的菜单信息并返回
        return sysMenuDao.findMenusByIds(menuIds);
    }
}
