package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "defaultGlobalFallbackMethod_80")
public class OrderHystrixController {

    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/comsumer/payment/hystrix/ok/{id}")
    public String getHyxtrixOK(@PathVariable("id") Long id) {
        String hystrixOk = orderHystrixService.getHystrixOk(id);
        log.info("result:"+hystrixOk);
        return hystrixOk;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    // 异常时回调函数,这里为处理时长超过3秒
    /*@HystrixCommand(fallbackMethod = "getHystrixTimeOut_Handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})*/
    // 加了fallbackMethod表示出异常调用默认的方法,不加表示调用默认的
    @HystrixCommand
    public String getHystrixTimeOut(@PathVariable("id") Long id) {
        Integer a = 10 /0;
        String hystrixTimeout = orderHystrixService.getHystrixTimeout(id);
        log.info("result:"+hystrixTimeout);
        return hystrixTimeout;
    }

    public String getHystrixTimeOut_Handler(@PathVariable("id") Long id) {
        return "线程池:"+Thread.currentThread().getName()+ "调用对方服务器超时或自身系统异常,端口号:80,请稍后再试,┭┮﹏┭┮";
    }

    public String defaultGlobalFallbackMethod_80() {
        return "global 全局处理异常,请检查自身环境信息,┭┮﹏┭┮";
    }

}
