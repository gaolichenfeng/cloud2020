package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableBinding(Sink.class)
public class StreamRabbitListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void consumerStreamRabbitMessage(Message<String> message) {
        System.out.println("**********consumer serial is:" + message.getPayload() +"\t" +
                "端口号port是:" + serverPort);
    }

}
