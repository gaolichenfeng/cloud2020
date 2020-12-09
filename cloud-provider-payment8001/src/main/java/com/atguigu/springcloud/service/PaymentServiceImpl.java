package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentMapper;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    public int create(Payment payment) {
        int i = paymentMapper.create(payment);
        return i;
    }

    public Payment getPayment(Long id) {
        Payment payment = paymentMapper.getPayment(id);
        return payment;
    }
}
