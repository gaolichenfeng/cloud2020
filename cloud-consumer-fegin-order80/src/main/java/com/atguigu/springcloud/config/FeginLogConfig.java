package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginLogConfig {

    @Bean
    public Logger.Level getLogger() {
        return Logger.Level.FULL;
    }
}
