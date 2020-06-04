package com.cy.sys.common.utils;

import com.cy.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @Auther: Administrator
 * @Date: 2020/4/1 13:17
 * @Description:
 */
public class ShiroUtils {
    public static String getUsername() {
        return getUser().getUsername();
    }

    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

}
