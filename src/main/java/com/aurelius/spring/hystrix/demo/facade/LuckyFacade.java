package com.aurelius.spring.hystrix.demo.facade;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LuckyFacade {
    private final static String GROUPED_COMMAND = "HystrixGroupedCommand";
    private final static String FALLBACK_METHOD = "unlucky";

    @Autowired
    private RestTemplate hystrixRestTemplate;

    @HystrixCommand(fallbackMethod = FALLBACK_METHOD, commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="3"),
    })
    public String getLuckyNumber() {
        System.out.println("getLuckyNumber Invoked");
        return hystrixRestTemplate.getForObject("http://localhost:8080/server/numbers", String.class);
    }

    @HystrixCommand(fallbackMethod = FALLBACK_METHOD, commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="3")
    })
    public String getLuckyName() {
        System.out.println("getLuckyName Invoked");
        return hystrixRestTemplate.getForObject("http://localhost:8080/server/name", String.class);
    }

    @HystrixCommand(groupKey = GROUPED_COMMAND, fallbackMethod = FALLBACK_METHOD, commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="3")
    })
    public String getGroupedStringOne() {
        System.out.println("getGroupedStringOne Invoked");
        return hystrixRestTemplate.getForObject("http://localhost:8080/server/randomOne", String.class);
    }


    @HystrixCommand(groupKey = GROUPED_COMMAND, fallbackMethod = FALLBACK_METHOD, commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="3")
    })
    public String getGroupedStringTwo() {
        System.out.println("getGroupedStringTwo Invoked");
        return hystrixRestTemplate.getForObject("http://localhost:8080/server/randomTwo", String.class);
    }

    private String unlucky() {
        return "It seems that you're unlucky. The server is down and this is hystrix fallback!";
    }

}
