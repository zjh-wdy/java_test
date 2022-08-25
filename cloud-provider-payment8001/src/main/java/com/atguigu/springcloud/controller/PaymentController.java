package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
//${server.port}  通过org.springframework.beans.factory.annotation.Value中的@Value注解可以取到applicaion.xml中的值

   @Value("${server.port}")
    private String serverPort ;

    @Resource
    PaymentService paymentService;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果*******："+result);
        if(result >0) {
            return  new CommonResult(200,"插入数据库成功!! serverPort："+serverPort,result);
        }else{
            return  new CommonResult(444,"插入数据库失败",null);
        }

    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id ){

        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果："+payment);
        if(payment !=null){
            return  new CommonResult(200,"查询数据成功！serverPort："+serverPort,payment);
        }else {
            return  new CommonResult(444,"查询数据失败",id);
        }

    }

    @GetMapping("/payment/discovery")
    public Object  discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {  //产生这个循环迭代器的  快捷代码  iter 直接回车就行；

            log.info("*********Eureka中的服务名称 service：" +service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
           log.info("############### CLOUD-PAYMENT-SERVICE 这个服务名称下的实例节点个数"+instance.getServiceId()+"\t" +instance.getHost()
           +"\t" +instance.getPort() +"\t" +instance.getUri() );

        }

        return  this.discoveryClient;

    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    //此类没有什么业务逻辑只是单纯的停止3s，模拟程序执行3s的超时；来展示OpenFeign的超时控制
    @GetMapping(value = "/payment/feign/timeout")
    public  String paymentFeignTimeout()  {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
