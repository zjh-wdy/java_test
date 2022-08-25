package com.atguigu.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//创建一个通用的后台向前台传值的实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    //比如后台返回失败到前台，通常是返回一个状态码和一个 成功失败的信息数据；
    //如  200   success;
    private Integer code;
    private String message;
    private T date;  //这些成泛型是为了，统配所有需要向前台传输的实体；

    public CommonResult(Integer code,String message){
        this(code,message,null);

    }
}