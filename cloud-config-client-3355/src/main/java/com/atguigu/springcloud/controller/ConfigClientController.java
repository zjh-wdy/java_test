package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

   @Value("${config.info}")
    private String configInfo;   //这里读取的是bootstrap.yml的config.info节点取到的配置信息

//
    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;    //这里返回的是从本类的属性的值，那这个值是从yml配置文件中获得的，而配置文件中的内容是从config-3344配置中心来拿到的；
    }

    @Value("${config.value}")
    private String configValue;   //这里读取的是bootstrap.yml的config.info节点取到的配置信息

    //
    @GetMapping("/configValue")
    public String getConfigValue()
    {
        return configValue;    //这里返回的是从本类的属性的值，那这个值是从yml配置文件中获得的，而配置文件中的内容是从config-3344配置中心来拿到的；
    }



}
