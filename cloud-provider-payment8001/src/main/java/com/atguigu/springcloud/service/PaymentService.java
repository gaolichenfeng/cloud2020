package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPayment(Long id);
}
