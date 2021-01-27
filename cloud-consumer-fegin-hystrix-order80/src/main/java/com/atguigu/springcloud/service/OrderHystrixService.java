package com.atguigu.springcloud.service;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface OrderHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getHystrixOk(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getHystrixTimeout(@PathVariable("id") Long id);
}
