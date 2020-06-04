package com.cy.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2020/3/24 19:56
 * @Description:
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -1307887171531509370L;
    private Long id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
