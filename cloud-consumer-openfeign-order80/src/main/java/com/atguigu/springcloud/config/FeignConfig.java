package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    Logger.Level feignLoggerLever()
    {
       return  Logger.Level.FULL; //设置成Full级别的，也就是每次服务消费端调用某个监控的接口，
                                // 服务台就会打印出除了HEADERS中定义的信息外，还有请求和响应的正文及元数据。
    }

}
