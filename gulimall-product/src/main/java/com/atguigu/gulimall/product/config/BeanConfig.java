package com.atguigu.gulimall.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;


@Configuration
public class BeanConfig {

    @Bean
    Model model(){
        Model model = new ConcurrentModel();
        return model;
    }
}
