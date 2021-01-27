package com.atguigu.springcloud.service;

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
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String getHystrixService_Timeout(Long id) {
        int a = 10 / 0;
        /*int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        String result = "线程池:" + Thread.currentThread().getName() + "Hystrix time out,呜呜┭┮﹏┭┮";
        return result;
    }

    public String getHystrixService_TimeoutHandler(Long id) {
        /*int threadNum = 5;
        try {
         TimeUnit.SECONDS.sleep(threadNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        String result = "线程池:" + Thread.currentThread().getName() + "服务器繁忙或者超时,请稍后再试,(灬ꈍ ꈍ灬)";
        return result;
    }
}
