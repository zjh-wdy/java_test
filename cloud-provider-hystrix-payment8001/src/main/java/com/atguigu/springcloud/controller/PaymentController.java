package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")   //注意这里的@value是  beans.factory.annotation.Value;这个包下面的，不是其他的包下面的；
    private String serverPort ;

//调用正常的访问方法
    @GetMapping(value="/payment/hystrix/ok/{id}")
    public String  paymentInfo_OK( @PathVariable("id") Integer id ){
        String result = paymentService.paymentInfo_OK(id);
        log.info("*******result*****"+result);  //从后台的控制台能看到result结果
        return  result;  //返回到前台也能够看到result
    }
//调用超时的方法
    @GetMapping(value="/payment/hystrix/timeout/{id}")
     public String paymentInfo_TimeOut(@PathVariable("id") Integer id ) {

         String result = paymentService.paymentInfo_TimeOut(id);
         log.info("*******result*****"+result);  //从后台的控制台能看到result结果
         return  result;  //返回到前台也能够看到result
      }


    //==================下面是服务熔断代码，上面讲解的是服务降级=========================

//调用到  我们在service中写的服务熔断的方法；来测试下服务熔断的效果；
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
}
