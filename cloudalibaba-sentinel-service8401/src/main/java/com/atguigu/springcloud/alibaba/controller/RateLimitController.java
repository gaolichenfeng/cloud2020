package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myhandler.customerException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")
    public CommonResult byResource() {
        return new CommonResult(200, "通过资源名byResource调用服务成功", new Payment(111L, UUID.randomUUID().toString()));
    }

    public CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, getClass().getCanonicalName(), new Payment(222l, "服务规则报错,调用指定异常方法"));
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "通过资源名byUrl调用服务成功");
    }

    @GetMapping("/customerHandler")
    @SentinelResource(value = "customerHandler",blockHandlerClass = customerException.class,blockHandler = "globalException2")
    public CommonResult getByCustomerHandler() {
        return new CommonResult(200, "客户自定义customer Handler 成功", new Payment(666L, UUID.randomUUID().toString()));
    }
}
