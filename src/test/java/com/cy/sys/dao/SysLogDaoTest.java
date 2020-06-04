package com.cy.sys.dao;

import com.cy.sys.entity.SysLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/18 19:06
 * @Description:
 */
@SpringBootTest
public class SysLogDaoTest {
    @Autowired
    SysLogDao sysLogDao;

    @Test
    public void testGetRowCount() {
        Long rows = sysLogDao.getRowCont(null);
        System.out.println("rows = " + rows);

    }

    @Test
    public void testFindPageObjects(){
        List<SysLog> logs = sysLogDao.findPageObjects("admin", 0L, 3);
        for (SysLog log : logs) {
            System.out.println(log);
        }
    }

    @Test
    public void testDeleteObjects(){
        int i = sysLogDao.deleteObjects(9L,10L);
        System.out.println("i = " + i);
    }
}
