package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {

    public String getHystrixService_OK(Long id) {
        String result = "线程池:" + Thread.currentThread().getName() + "Hystrix ok, id: " + id + "O(∩_∩)O哈哈~";
        return result;
    }

    // 异常时回调函数,这里为处理时长超过3秒
    @HystrixCommand(fallbackMethod = "getHystrixService_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String getHystrixService_Timeout(Long id) {
//        int a = 10 / 0;
        //int timeNumber = 5;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "线程池:" + Thread.currentThread().getName() + ",端口号:8001,Hystrix time out,正常调用未超时,O(∩_∩)O哈哈~";
        return result;
    }

    public String getHystrixService_TimeoutHandler(Long id) {
        /*int threadNum = 5;
        try {
         TimeUnit.SECONDS.sleep(threadNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        String result = "线程池:" + Thread.currentThread().getName() + ",端口号8001,服务器繁忙或者超时,请稍后再试,(灬ꈍ ꈍ灬)";
        return result;
    }


    @HystrixCommand(fallbackMethod = "paymentCircuitFaiiback_handler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitFaiiback(Long id) {
        if (id < 0) {
            throw new RuntimeException("输入参数有误,小于零");
        }
        String serial = IdUtil.simpleUUID();
        String result = "线程池:" + Thread.currentThread().getName() + "Hystrix 熔断器调用测试成功,"+"\t"+"流水号是:"+serial;
        return result;
    }

    public String paymentCircuitFaiiback_handler(Long id) {
        return "传入参数id,不能为负数,调用失败┭┮﹏┭┮,id:"+id;
    }
}
