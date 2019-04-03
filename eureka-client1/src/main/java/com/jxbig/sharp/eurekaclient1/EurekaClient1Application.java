package com.jxbig.sharp.eurekaclient1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@RefreshScope
public class EurekaClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient1Application.class, args);
    }

    @Value("${server.port}")
    private String port;
    @Value("${sharp.version}")
    private String version;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/twoHi")
    public String twoHi() {
        return "hi, i'm zipkin two";
    }

    @RequestMapping("/twoInfo")
    public String twoInfo() {
        return restTemplate.getForObject("http://localhost:8911/oneHi", String.class);
    }

    @RequestMapping("/")
    public String healthy() {
        return "i' ok, port: " + port + ", version: " + version;
    }
}
