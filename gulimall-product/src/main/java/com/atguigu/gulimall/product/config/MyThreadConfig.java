package com.atguigu.gulimall.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wuyong
 * @Description: 线程池配置类
 * @email wu4yong@163.com
 * @date 2023-04-02 12:20:38
 */
@Configuration
public class MyThreadConfig {

    @Value("${gulimall.thread.coreSize}")
    private Integer coreSize;

    @Value("${gulimall.thread.maxSize}")
    private Integer maxSize;

    @Value("${gulimall.thread.keepAliveTime}")
    private Integer keepAliveTime;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                coreSize,
                maxSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
