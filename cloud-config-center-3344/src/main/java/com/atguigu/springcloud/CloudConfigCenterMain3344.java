package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigCenterMain3344.class,args);
    }
}
