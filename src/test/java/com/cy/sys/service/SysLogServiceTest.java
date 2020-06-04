package com.cy.sys.service;

import com.cy.sys.common.vo.PageObject;
import com.cy.sys.entity.SysLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 12:04
 * @Description:
 */
@SpringBootTest
public class SysLogServiceTest {
    @Autowired
    private SysLogService sysLogService;
    @Test
    public void testFindPageObjects() {
        PageObject<SysLog> pageObject=
                sysLogService.findPageObjects("admin", 3L);
        System.out.println(pageObject);

    }

}
