package com.cy.sys.service;


import com.cy.sys.common.vo.Node;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.entity.SysUserMenuVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 19:30
 * @Description:
 */
public interface SysMenuService {
    int updateObjecy(SysMenu sysMenu);

    int saveObject(SysMenu sysMenu);

    List<Map<String, Object>> findObjects();

    int deleteObject(Long id);

    List<Node> findZtreeMenuNodes();

    List<SysUserMenuVo> findUserMenusByUserId(Integer id);
}
