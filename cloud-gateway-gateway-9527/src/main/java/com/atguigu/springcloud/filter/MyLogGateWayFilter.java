package com.atguigu.springcloud.filter;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component  //这里一定要写上这个注解，这因为不写这个注入的话，spring是不认识这类的，也就是不会发挥作用
@Slf4j
public class MyLogGateWayFilter  implements GlobalFilter, Ordered {

    /*
    *
    * 做了一个总的全局过滤器，等待每一个请求过来后，都会走这个过滤器进行过滤；满足调减继续走，不满足的话返回，被阻止继续访问
    * */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********come in MyLogGateWayFilter: "+new Date());
        //取出请求参数的username对应的值
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        //如果uanme为空，就直接过滤掉，不走路由
        if(username==null){
            log.info("*****用户名为Null 非法用户,(┬＿┬)");
            //判断该请求不通过时：给一个回应，返回
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(HttpStatus.HTTP_NOT_ACCEPTABLE));

            return  exchange.getResponse().setComplete();
        }
        //反之，调用下一个过滤器，也就是放行：在该环节判断通过的exchange放行，交给下一个filter判断
        return chain.filter(exchange);
    }


    /**
     * 这个过滤器的加载顺序，数字越小，优先级越高
     * 设置这个过滤器在Filter链中的加载顺序。
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
