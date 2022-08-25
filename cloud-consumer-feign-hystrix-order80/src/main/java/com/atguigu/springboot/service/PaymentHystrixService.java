package com.atguigu.springboot.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component   //加上次注解是为了让spring来识别到这个服务组件
@FeignClient(value="CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)  //加上这个注解是为了对远程调用的微服务统一的提供一个服务降级的通用的处理类，处理类中有对所有的远程接口的服务降级过程
public interface PaymentHystrixService {

    @GetMapping(value="/payment/hystrix/ok/{id}")
    public String  paymentInfo_OK( @PathVariable("id") Integer id );

    @GetMapping(value="/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id ) ;

}
