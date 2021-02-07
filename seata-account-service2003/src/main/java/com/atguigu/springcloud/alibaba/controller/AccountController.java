package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.dao.Order;
import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestBody Order order) {
        accountService.decrease(order.getUserId(), order.getMoney());
        return new CommonResult(200,"调用seata AccountController 成功");

    }
}
