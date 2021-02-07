package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        /*try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "----------------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" +"...testB");
        return "---------------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int age = 10/0;
        log.info(Thread.currentThread().getName() +"\t" + "--------testD一场比例");
        return "---------------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        int age = 100/0;
        log.info(Thread.currentThread().getName() +"\t" + "testE异常数");
        return "test异常数";
    }

    /**
     *  @SentinelResource(value = "testHotKey") value可以随便配,唯一就行
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(name = "p1",required = false) String p1,
                             @RequestParam(name = "p2",required = false) String p2) {
        int age = 10/0;
        return "-----------test Hotkey";
    }

    //这里没有getmapping,所以不需要+@RequestParam注解
    public String deal_testHotKey(String p1, String p2, BlockException blockException) {
        return "deal hot key fail Ծ‸Ծ";
    }
}
