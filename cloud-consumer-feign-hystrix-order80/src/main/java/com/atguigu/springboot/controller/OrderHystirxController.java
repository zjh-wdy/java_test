package com.atguigu.springboot.controller;

import com.atguigu.springboot.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_Method")  //想要配置统一的服务降级的fallback方法，需要设置默认的fallback方法；
public class OrderHystirxController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
   /* @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_fallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/   //配置统一的fallback参数后，这里就不需要这个配置了，  如果这具体的指明了想要用fallback方法的参数的话，就会使用指定的这个兜底调用方法了；
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_Timeout_fallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者cloud-consumer-feign-hystrix-order80服务。对方支付系统繁忙，请10秒之后再尝试或者自己运行出错，请检查自己，o(╥﹏╥)o";
    }


    //下面是全局fallback的调用方法
    public String   payment_Global_Method(){
        return  "Global 异常处理信息，请稍后再试    o(╥﹏╥)o";
    }

}
