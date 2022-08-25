package com.atguigu.springcloud.service;



import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

//正常访问的方法，下面的是模拟故障的访问方法；
    public String  paymentInfo_OK( Integer id ){
        //注意这里使用的是   java.lang包下面的Thread线程类
        return  "线程池： "+ Thread.currentThread().getName() +" PaymentInfo_OK "+  id+ "\t"+"O(∩_∩)O哈哈~";
    }

////模拟拥堵的情况 ：    模拟是个复杂的业务处理，一般复杂的程序都需要执行3s才会返回的方法；
    //fallbackMethod  设置此方法超时后的兜底方法；
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler" ,commandProperties = {
            //下面这个配置是设置下面的这个方法的超时时间，等方法实际的执行时间超过这个超时时间后，系统会调用默认的fallback接口，而不是还是调用此方法，给前台返回错误
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id ) {

        //1/验证：执行超时后的兜底效果
       int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "线程池： "+Thread.currentThread().getName() +"paymentInfo_TimeOut"+id+"\t"+ "O(∩_∩)O哈哈~" +"耗时（单位：秒）："+timeNumber;

        //2 验证：执行报错后的兜底效果
       /* int age=10/0;  //此处会报错；

        return  "线程池： "+Thread.currentThread().getName() +"paymentInfo_TimeOut"+id+"\t"+ "O(∩_∩)O哈哈~" +"耗时（单位：秒）：";*/
    }

    public String paymentInfo_TimeoutHandler(Integer id ) {
        return  "线程池： "+Thread.currentThread().getName() +"系统繁忙，请稍后再试"+id+"\t"+ "o(╥﹏╥)o" ;

    }


    //==================下面来讲解服务熔断，上面讲解的是服务降级=========================

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold" ,value ="10"), //最小请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),  //短路多久以后开始尝试是否恢复，默认5s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  //失败率达到多少后跳闸
    })//总的意思就是在n毫秒内的时间窗口期内，m次请求中有p%的请求失败了，那么断路器启动
    public  String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if( id <0){
            throw  new RuntimeException("******id不能为负数");//自己通过传输的参数是负数来触发运行中异常，来达到调用备用方法的效果
        }
        String serialNumber = IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t" +"调用成功，流水号："+serialNumber;


    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id  不能为负数，请稍后在重试！====："+id;

    }

}
