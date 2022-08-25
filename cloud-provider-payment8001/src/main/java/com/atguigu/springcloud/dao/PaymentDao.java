package com.atguigu.springcloud.dao;



import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //注解推荐使用这个，之前一般使用的是 @Repository ,但是使用@Mapper更优于使用@Repository
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id );


}



