package com.cy.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2020/3/27 15:43
 * @Description:
 */
@Data
public class SysUserDeptVo implements Serializable {
    private static final long serialVersionUID = -1994859233406899031L;
    private Integer id;
    private String username;
    private String password;//md5
    private String salt;
    private String email;
    private String mobile;
    private Integer valid=1;
    private SysDept sysDept; //private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
