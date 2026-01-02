package com.keniu;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "unstableService", fallbackMethod = "fallback")
    public String callUnstable() {
        return restTemplate.getForObject(
                "http://localhost:8081/unstable",
                String.class
        );
    }

    public String fallback(Throwable throwable) {
        System.out.println("In fallback");
        return "DEFAULT_RESPONSE";
    }
}
