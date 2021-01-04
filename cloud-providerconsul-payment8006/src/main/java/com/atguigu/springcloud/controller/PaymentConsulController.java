package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentConsulController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/payment/consul")
    public String getPaymentConsul() {
        return "payment controller consul port:"+port+"/t"+ UUID.randomUUID().toString();
    }

}
