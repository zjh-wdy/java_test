package com.atguigu.controller;

import com.atguigu.config.ApplicationContextConfig;
import com.atguigu.springcloud.entities.*;
import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

   // public static  final String PATMENT_URL="http://localhost:8001";
    public static  final String PATMENT_URL="http://cloud-payment-service";  //之前payment单点的时候，单独
    //单独写成上面，目前是集群的方式，所以我们需要把这里直接写成服务名称，让负载均衡的机制，去自己找访问哪个端口的微服务；

    @Resource
    RestTemplate restTemplate;
    //------------------ postForObject() --------------------------------------


    //由于是客户端，所以发的都是get请求，所以下面使用GetMapping
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //由于浏览器只能发送get请求，但是看方法体中的内容，调用实质是发送的post请求；
        log.info("*********调用8001 create  test1开始***********");
        return  restTemplate.postForObject(PATMENT_URL+"/payment/create",payment,CommonResult.class);

    }

    //------------------ postForEntity() --------------------------------------

    //由于是客户端，所以发的都是get请求，所以下面使用GetMapping
    @GetMapping("/consumer/payment/postForEntity/create")
    public CommonResult<Payment> create2(Payment payment){
        //由于浏览器只能发送get请求，但是看方法体中的内容，调用实质是发送的post请求；
        log.info("*********调用8001 create  create2开始***********");
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PATMENT_URL+"/payment/create",payment,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return  entity.getBody();

        }else {
            return  new CommonResult<>(444,"插入失败");
        }



    }



//------------------ getForObject() --------------------------------------
    //调用远程的get方法
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id ){
        log.info("*********调用8001开始 getPayment开始***********");
        // // 返回对象为响应体中数据转化成的对象，基本上可以理解为JSON
      return   restTemplate.getForObject(PATMENT_URL+"/payment/get/"+id,CommonResult.class);

    }

    //使用 RestTemplate的  getForEntity进行远程访问
//------------------ getForEntity() --------------------------------------
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id ){
        log.info("*********调用8001开始 getPayment2  开始***********");
        //// 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头，响应状态码，响应体等。
        ResponseEntity<CommonResult> entity  = restTemplate.getForEntity(PATMENT_URL+"/payment/get/"+id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info("getHeaders():"+entity.getHeaders().toString()+"     getStatusCode():"+entity.getStatusCode());
            return entity.getBody();

        }else {
            return  new CommonResult<>(4444,"操作失败");
        }



    }


}
