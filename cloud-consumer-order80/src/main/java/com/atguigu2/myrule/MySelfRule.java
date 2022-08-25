package com.atguigu2.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule getIRule(){
        return  new RandomRule(); //定义为随机负载均衡算法,之前这里没有指定之前是默认的轮训算法进行负载均衡

    }
}
