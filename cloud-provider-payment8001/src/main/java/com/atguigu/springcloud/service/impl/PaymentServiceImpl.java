package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl  implements PaymentService {
    //之前使用的是@autowire 这个是spring的，我们现在使用的是spring自己的自动注入的注解；
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        if(payment.getSerial()==null){
            payment.setSerial("尚硅谷创建");
        }

        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id ){
        return  paymentDao.getPaymentById(id);

    }


}
