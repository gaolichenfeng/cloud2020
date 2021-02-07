package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PaymentProviderController {
    @Value("${server.port}")
    private String serverPort;

    private static Map<Long,Object> map = new HashMap<>();

    static {
        map.put(1L,new Payment(1L, UUID.randomUUID().toString()));
        map.put(2L,new Payment(2L, UUID.randomUUID().toString()));
        map.put(3l,new Payment(3L, UUID.randomUUID().toString()));
    }

    @GetMapping("/paymentSql/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {

        Payment payment = (Payment) map.get(id);
         CommonResult<Payment> result = new CommonResult(200,"get payment by sql success ----" +serverPort,payment);
        return result;
    }
}
