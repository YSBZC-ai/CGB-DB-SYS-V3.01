package com.cy.sys.service.impl;

import com.cy.sys.common.exception.ServiceException;
import com.cy.sys.common.vo.CheckBox;
import com.cy.sys.common.vo.PageObject;
import com.cy.sys.dao.SysRoleDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysRole;
import com.cy.sys.entity.SysRoleMenuVo;
import com.cy.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/24 20:08
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObjects();
    }

    @Override
    public int updateObject(SysRole entity, Long[] menuIds) {
        //1.合法性验证
        if(entity==null)
            throw new IllegalArgumentException("更新的对象不能为空");
        if(entity.getId()==null)
            throw new IllegalArgumentException("id的值不能为空");

        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不能为空");
        if(menuIds==null||menuIds.length==0)
            throw new IllegalArgumentException("必须为角色指定一个权限");

        //2.更新数据
        int rows=sysRoleDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("对象可能已经不存在");
        sysRoleMenuDao.deleteObjectByRoleId(entity.getId());
        sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
        //3.返回结果
        return rows;

    }

    @Override
    public SysRoleMenuVo findObjectById(Long id) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new IllegalArgumentException("id的值不合法");
        //2.执行查询
        SysRoleMenuVo result=sysRoleDao.findObjectById(id);
        //3.验证结果并返回
        if(result==null)
            throw new ServiceException("此记录已经不存在");
        return result;
    }

    @Override
    public int saveObject(SysRole entity, Long[] menuIds) {
        //1.参数有效性校验
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不允许为空");
        if(menuIds==null||menuIds.length==0)
            throw new ServiceException("必须为角色分配权限");
        //2.保存角色自身信息
        int rows=sysRoleDao.insertObject(entity);
        //3.保存角色菜单关系数据
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        //4.返回业务结果
        return rows;

    }

    @Override
    public int deleteObject(Long id) {
        //1.验证数据的合法性
        if(id==null||id<=0)
            throw new IllegalArgumentException("请先选择");
        //3.基于id删除关系数据
        sysRoleMenuDao.deleteObjectByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        //4.删除角色自身
        int rows=sysRoleDao.deleteObject(id);
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        //5.返回结果
        return rows;
    }


    @Override
    public PageObject<SysRole> roleFindPageObjects(String username, Long pageCurrent) {
        //1.对参数进行校验
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前页码值无效");
        //2.查询总记录数并进行校验
        long rowCount = sysRoleDao.getRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("没有找到对应记录");
        //3.查询当前页记录
        int pageSize = 2;
        long startIndex = (pageCurrent - 1) * pageSize;
        List<SysRole> records =
                sysRoleDao.findPageObjects(username,
                        startIndex, pageSize);
        //4.对查询结果进行封装并返回
        return new PageObject<SysRole>(pageCurrent, pageSize, rowCount, records);
    }
}
