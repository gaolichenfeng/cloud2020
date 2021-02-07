package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface StorageService {
    void decrease(Long productId, Integer count);
}
