package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextBean {

    @Bean
    @LoadBalanced  //作用： RestTemplate上添加注解@LoadBalanced 注解表示 开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
