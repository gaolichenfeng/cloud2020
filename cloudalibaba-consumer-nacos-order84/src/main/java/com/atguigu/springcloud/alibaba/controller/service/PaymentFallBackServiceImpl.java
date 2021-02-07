package com.atguigu.springcloud.alibaba.controller.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackServiceImpl implements ConsumerOpenFeginService {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(44444, "远程调用服务名为nacos-payment-provider异常,method is ---"
                + PaymentFallBackServiceImpl.class,new Payment(id, id +"serial is null"));
    }
}
