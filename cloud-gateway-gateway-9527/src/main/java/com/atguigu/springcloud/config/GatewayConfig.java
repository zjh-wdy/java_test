package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    /**
     * 配置了一个id为path_route_atguigu的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        //构建一个路由器，这个routes相当于yml配置文件中的routes
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //路由器的id是：path_route_atguigu，规则是我现在访问/guonei，将会转发到http://news.baidu.com/guonei
        // 这里的id、path、uri都可以跟yml中的配置对上
        // 通过localhost:9527 映射 http://news.baidu.com
        routes.route("path_route_atguigu",
                r->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();


        return routes.build();
    }
    /*
    * 配置一个id为  path_route_atguigu_xinlang的路由规则，
    * 当访问地址：http://localhost:9527/myxinlang  时会转发到地址：https://news.sina.com.cn/world/
    * */

    @Bean
    public RouteLocator customRouteLocatorToXinLang(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu_xinlang",
                r->r.path("/myxinlang")
                        .uri("https://news.sina.com.cn/world/")).build();
        return  routes.build();

    }

}
