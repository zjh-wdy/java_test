package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {

    //快捷键：mainboot
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }



}
