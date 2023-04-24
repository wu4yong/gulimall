package com.atguigu.gulimall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wuyong
 * @Description: 线程池配置类
 * @email wu4yong@163.com
 * @date 2023-04-02 12:20:38
 */

@ConfigurationProperties(prefix = "gulimall.thread")
// @Component
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;


}
