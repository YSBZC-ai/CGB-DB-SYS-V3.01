package com.cy.sys.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2020/3/23 20:43
 * @Description:
 */
@Data
public class Node implements Serializable {
    private static final long serialVersionUID = 7982407616958489412L;
    private Integer id;
    private String name;
    private Integer parentId;

}
