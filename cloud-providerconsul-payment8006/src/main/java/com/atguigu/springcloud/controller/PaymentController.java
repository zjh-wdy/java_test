package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul() {
        //这里代表如果我们这个微服务真正的注册进zookeeper了，那每一次我们访问这个url，那就会从后台这里返回到浏览器相关的json串，
        return "spring with consul:" + serverPort + "\t" + UUID.randomUUID().toString();

    }
}
