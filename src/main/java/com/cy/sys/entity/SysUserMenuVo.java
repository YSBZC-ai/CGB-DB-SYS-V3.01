package com.cy.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/4/1 16:48
 * @Description:
 */
@Data
public class SysUserMenuVo implements Serializable {
    private static final long serialVersionUID = 4161648376828271548L;
    private Integer id;
    private String name;
    private String url;
    private List<SysUserMenuVo> childs;

}
