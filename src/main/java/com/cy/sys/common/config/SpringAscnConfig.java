package com.cy.sys.common.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: Administrator
 * @Date: 2020/3/31 11:21
 * @Description:
 */
@Slf4j
@Setter
@Configuration
@ConfigurationProperties("async-thread-pool")
public class SpringAscnConfig implements AsyncConfigurer {
    /**
     * 核心线程数
     */
    private int corePoolSize = 20;
    /**
     * 最大线程数
     */
    private int maximumPoolSize = 1000;
    /**
     * 线程空闲时间
     */
    private int keepAliveTime = 30;
    /**
     * 阻塞队列容量
     */
    private int queueCapacity = 200;
    private String threadNamePrefix = "task===>";

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maximumPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(
                (Runnable r, ThreadPoolExecutor exe) -> {
                    log.warn("当前任务线程池队列已满.");
                }
        );
        executor.initialize();
        return executor;
    }

}
