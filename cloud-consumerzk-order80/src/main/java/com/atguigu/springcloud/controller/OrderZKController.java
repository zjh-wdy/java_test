package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZKController {

    //快捷键psf  是产生public static final  的快捷键
    //书写我们的调用地址
    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    //1、接下来要显实现验证的部分：order调用payment成功，2、order注册早zk成功；
    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo(){

      //为了实现从order远程调用到微服务payment，使用restTemplate这个bean这个对象，
        //具体调用的话是使用 restTemplate.getForObject（）方法 ，里面的第一个参数是调用payment的访问接口
        //一个是调用完毕后返回的结果类型 为string，因为在payment这个微服务中/payment/zk 中调用的方法返回的是string类型
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    //上述结果有返回后，这里就可以返回到浏览器进行显示了；
        return  result;
    }

}
