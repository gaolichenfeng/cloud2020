package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class ProviderDownFaiiBack implements OrderHystrixService {
    @Override
    public String getHystrixOk(Long id) {
        return "方法getHystrixOk调用8001服务器失败,其宕机了,呜呜呜";
    }

    @Override
    public String getHystrixTimeout(Long id) {
        return "方法getHystrixTimeout调用8001服务器失败,其宕机了,呜呜呜";
    }
}
