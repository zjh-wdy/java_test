package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component  //为了使得此服务组件注册进spring，让spring能认识
@FeignClient(value="CLOUD-PAYMENT-SERVICE")   //cloud-payment-service   这里是写成微服务的提供者在Eureka上的注册的服务名称
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")  //这里的OpenFeign-order80此接口访问地址和8001服务提供者控制层的访问value值是一样的
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);  //泛型中具体指明对象的类型，因为feign接口返回的对象要比服务提供层更详细

    @GetMapping(value = "/payment/feign/timeout")
    public  String paymentFeignTimeout();

}
