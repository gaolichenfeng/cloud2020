package com.atguigu.myrule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    @Bean
    @LoadBalanced
    public RandomRule getRandomRule() {
        return new RandomRule();
    }
}
