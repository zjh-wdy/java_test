package com.atguigu.springboot.service;

import org.springframework.stereotype.Component;

@Component//一定要记得加上这个注解，否则的话SpringBoot扫描不到这个类，不会注入这个bean的；
public class PaymentFallbackService  implements  PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PaymentFallbackService-paymentInfo_Ok, fallback  ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentFallbackService-paymentInfo_TimeOut, fallback";
    }
}
