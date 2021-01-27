package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
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
    public String getHystrixTimeOut(@PathVariable("id") Long id) {
        String hystrixTimeout = orderHystrixService.getHystrixTimeout(id);
        log.info("result:"+hystrixTimeout);
        return hystrixTimeout;
    }
}
