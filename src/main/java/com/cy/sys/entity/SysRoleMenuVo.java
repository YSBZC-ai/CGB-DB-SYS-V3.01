package com.cy.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/27 09:22
 * @Description:用户封装角色及角色对应的菜单信息
 */
@Data
public class SysRoleMenuVo implements Serializable {

    private static final long serialVersionUID = -6233670819817882231L;
    private Long id;
    private String name;
    private String note;
    private List<Long> menuIds;
}
