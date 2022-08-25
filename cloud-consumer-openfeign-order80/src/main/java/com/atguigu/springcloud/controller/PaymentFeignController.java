package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentFeignController {

    @Resource
    PaymentFeignService paymentFeignService;     //这里注入feign的接口对象paymentFeignService。
                                              // 通过在服务消费者方的paymentFeignService服务中去找到  @FeignClient(value="CLOUD-PAYMENT-SERVICE") 注解对应在Eureka中注册的微服务名称
                                             // 这是已经找到了调用的微服务，之后再确定访问微服务提供者的什么接口，具体就是找这个service中的 url注解就行
    //                                        最终实现 对order自己的对应的方法名称调用
                                              //来调用到远程的服务提供者对应的接口服务；

    @GetMapping(value =  "/consumer/payment/get/{id}")  // 这里是浏览器实际访问的地址
    public CommonResult<Payment> getPaymentById (@PathVariable("id") Long id){
        return  paymentFeignService.getPaymentById(id);  //消费者侧通过 调用OpenFeign的远程调用接口来访问远程的服务提供者的接口

    }
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public  String paymentFeignTimeout()  {
        //openfeign-ribbon，客户端一般默认等待1s
        return  paymentFeignService.paymentFeignTimeout();
    }

}
