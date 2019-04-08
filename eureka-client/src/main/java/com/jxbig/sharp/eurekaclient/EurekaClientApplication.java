package com.jxbig.sharp.eurekaclient;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@RefreshScope
@RequestMapping("/${request.prefix}")
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Value("${server.port}")
    private String port;
    @Value("${sharp.version}")
    private String version;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/oneHi")
    public String oneHi() {
        return "i'm service-hi";
    }

    @RequestMapping("/oneInfo")
    public String oneInfo() {
        return restTemplate.getForObject("http://localhost:8915/twoHi", String.class);
    }

    @RequestMapping("/")
    public String healthy() {
        return "i' ok, port: " + port + ", version: " + version;
    }
}
