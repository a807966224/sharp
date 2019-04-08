package com.jxbig.sharp.eurekaribbon.manager.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "oneHiError")
    public String oneHi() {
        return restTemplate.getForObject("http://eureka-client/A/oneHi", String.class);
    }
    public String oneHiError() {
        return "oneHiError........";
    }

}
