package com.itguigu.springcloud.controller;

import com.itguigu.springcloud.service.SendMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StreamRabbitmqController {

    @Resource
    private SendMessageProvider sendMessageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return sendMessageProvider.send();
    }

}
