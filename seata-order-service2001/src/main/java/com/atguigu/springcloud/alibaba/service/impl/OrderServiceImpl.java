package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional
    public void create(Order order) {
        log.info("开始创建订单");
        orderDao.create(order);
        log.info("创建订单结束");

        log.info("开始扣减账户金额");
        accountService.decrease(order);
        log.info("账户金额扣减结束");

        log.info("开始扣减库存数量");
        storageService.decrease(order);
        log.info("库存数量扣减结束");

        log.info("开始修改订单状态");
        Long userId = order.getUserId();
        orderDao.update(userId,1);
        log.info("修改订单状态结束");

        log.info("订单流程结束了.....");
    }
}
