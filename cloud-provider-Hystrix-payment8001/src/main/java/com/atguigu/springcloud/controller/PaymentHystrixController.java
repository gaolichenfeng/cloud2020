package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getHystrixOk(@PathVariable("id") Long id) {
        String hystrixService_ok = paymentHystrixService.getHystrixService_OK(id) +"---"+serverPort;
        log.info(hystrixService_ok);
        return hystrixService_ok;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getHystrixTimeout(@PathVariable("id") Long id) {
        String hystrixService_timeout = paymentHystrixService.getHystrixService_Timeout(id)+"---"+serverPort;
        log.info(hystrixService_timeout);
        return hystrixService_timeout;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitFaiiback(@PathVariable("id") Long id) {
        String result = paymentHystrixService.paymentCircuitFaiiback(id);
        log.info("******result:"+result);
        return result;
    }
}
