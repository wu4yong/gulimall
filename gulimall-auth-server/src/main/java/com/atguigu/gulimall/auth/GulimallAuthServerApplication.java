package com.atguigu.gulimall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 核心原理
 * 1）、@EnableRedisHttpSession导入RedisHttpSessionConfiguration配置
 *      1、给容器中添加了一个组件
 *          SessionRepository--》RedisOperationsSessionRepository--》Redis操作session，session的增删改查封装类
 *      2、SessionRepositoryFilter： session存储过滤器，每个请求过来都必须经过过滤器
 *          1、创建的时候，就自动从容器获取到了SessionRepository
 *          2、原生的request，response都被包装了SessionRepositoryRequestWrapper,  SessionRepositoryResponseWrapper
 *          3、以后获取session，request.getsession()
 *          4、wrapperRequest.getsession()==》SessionRepository 获取到的
 *  装饰者模式：
 *
 *
 *
 */

//@EnableRedisHttpSession     //整合Redis作为session存储
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAuthServerApplication.class, args);
    }

}
