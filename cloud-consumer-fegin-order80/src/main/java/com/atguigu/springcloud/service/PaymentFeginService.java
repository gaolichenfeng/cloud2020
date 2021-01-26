package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "cloud-payment-service")
public interface PaymentFeginService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPayment(@PathVariable(name = "id") Long id);

    @GetMapping("/payment/fegin/timeout")
    String getFeginTimeOut();
}
