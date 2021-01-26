package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //private static final String PAYMENT_PROVIDER = "http://localhost:8001"; 单机版
    private static final String PAYMENT_PROVIDER = "http://cloud-payment-service"; //负载均衡版


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info(payment.toString());
        return restTemplate.postForObject(PAYMENT_PROVIDER + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_PROVIDER+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getentity/{id}")
    public CommonResult<Payment> getPaymentByEntity(@PathVariable(name = "id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_PROVIDER + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" +entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult<Payment>(405,"服务调用失败");
        }
    }

    @PostMapping("/consumer/payment/forentity/create")
    public CommonResult<Payment> createByEntity(@RequestBody Payment payment) {
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_PROVIDER + "/payment/create", payment, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info(responseEntity.getStatusCode() + "\t" +responseEntity.getHeaders());
            return responseEntity.getBody();
        } else {
            return new CommonResult<Payment>(405,"服务创建调用失败");
        }
    }


}
