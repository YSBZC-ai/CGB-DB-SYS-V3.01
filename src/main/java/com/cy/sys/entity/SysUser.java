package com.cy.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2020/3/28 10:06
 * @Description:
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 5504953670163531396L;
    private Long id;
    private String username;
    private String password;
    private String salt;//盐值
    private String email;
    private String mobile;
    private Integer valid=1;
    private Long deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
