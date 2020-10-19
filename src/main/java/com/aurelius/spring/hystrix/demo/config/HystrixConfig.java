package com.aurelius.spring.hystrix.demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCircuitBreaker
public class HystrixConfig {

    @Bean
    public RestTemplate hystrixRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
