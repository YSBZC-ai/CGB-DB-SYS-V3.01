package com.cy.sys.service;

import com.cy.sys.common.vo.PageObject;
import com.cy.sys.entity.SysLog;

import java.util.concurrent.Future;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 09:48
 * @Description:
 */
public interface SysLogService {

    PageObject<SysLog> findPageObjects(String name, Long pageCurrent);

    int deleteObject (Long...ids);

    Future<Integer> saveObject(SysLog entity);

}
