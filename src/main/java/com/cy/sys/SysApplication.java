package com.cy.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync //spring容器启动时会创建线程池
/**
 * 异步的自动配置生效).
 * @EnableCaching 注解表示启动缓存配置
 */
@EnableCaching
@SpringBootApplication
public class SysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }

}
