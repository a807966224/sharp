package com.jxbig.sharp.eurekaclient3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/${request.prefix}")
public class EurekaClient3Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient3Application.class, args);
    }

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/twoHi")
    public String twoHi() {
        return "hi, i'm zipkin two";
    }

    @RequestMapping("/twoInfo")
    public String twoInfo() {
        return restTemplate.getForObject("http://localhost:8970/oneHi", String.class);
    }
}
