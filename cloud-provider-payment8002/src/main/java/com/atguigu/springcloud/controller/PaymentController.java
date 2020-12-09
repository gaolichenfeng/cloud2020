package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:"+i);
        if (i > 0) {
            return new CommonResult(200,"success,serverPort:"+serverPort,payment);
        } else {
            return new CommonResult(444,"fail",payment);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPayment(id);
        log.info("返回结果:"+payment+"\t"+"qwq");
        if (null == payment) {
            return new CommonResult(444,"fail"+id,null);
        } else {
            return new CommonResult(200,"success,serverPort:"+serverPort,payment);
        }
    }
}
