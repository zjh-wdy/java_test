package com.atguigu.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //使用 LoadBalanced 这个注解来赋予了  RestTemplate的负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
//上面的就相当于 ApplicationContext.xml中的<bean id=""  class="">等bean的注入；