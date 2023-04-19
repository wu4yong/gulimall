package com.atguigu.gulimall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 想要调用远程服务的接口
 * 1：引入feign
 * 2：编写一个接口，告诉springcloud这个接口需要调用远程服务的 名称和接口地址
 * 3：开启远程调用功能@EnableFeignClients
 */
@MapperScan("com.atguigu.gulimall.member.dao")//添加mybatis文件扫描
@EnableFeignClients(basePackages = "com.atguigu.gulimall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class  GulimallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class, args);
    }

}
