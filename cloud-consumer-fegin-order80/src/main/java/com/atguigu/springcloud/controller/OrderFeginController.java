package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeginController {

    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(path = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentByFegin(@PathVariable(name = "id") Long id) {
        CommonResult<Payment> payment = paymentFeginService.getPayment(id);
        return payment;
    }

    @GetMapping("/consumer/payment/fegin/timeout")
    public String getPaymentFeginTimeout() {
        String feginTimeOut = paymentFeginService.getFeginTimeOut();
        return feginTimeOut;
    }
}
