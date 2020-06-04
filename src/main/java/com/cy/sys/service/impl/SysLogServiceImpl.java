package com.cy.sys.service.impl;

import com.cy.sys.common.exception.ServiceException;
import com.cy.sys.common.vo.PageObject;
import com.cy.sys.dao.SysLogDao;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 09:57
 * @Description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogDao sysLogDao;

    @Override
    public int deleteObject(Long... ids) {
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("请选择至少一个记录");
        }
        int row;
        try {
            row = sysLogDao.deleteObjects(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("系统可能出现故障，恢复中...");
        }
        if (row == 0) {
            throw new ServiceException("没有找到对应的数据");
        }
        return row;
    }

    @Override
    public PageObject<SysLog> findPageObjects(String username, Long pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码不正确!");
        }
        Long rowCount = sysLogDao.getRowCont(username);
        if (rowCount == 0) {
            throw new ServiceException("系统没有查到对应记录!");
        }
        Integer pageSize = 3;
        Long startIndex = (pageCurrent - 1) * pageSize;
        List<SysLog> sysLogs;
        try {
            sysLogs = sysLogDao.findPageObjects(username, startIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("系统可能出现故障，恢复中...");
        }
        PageObject<SysLog> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        pageObject.setRecords(sysLogs);

        return pageObject;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Future<Integer> saveObject(SysLog entity) {
        String threadName = Thread.currentThread().getName();
        System.out.println("logThreadName = " + threadName);
        int rows = sysLogDao.insertObject(entity);
        return new AsyncResult<>(rows);
    }
}