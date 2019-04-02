package com.jxbig.sharp.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心：
 *
 * 1. 一组4人，达到4人即成组
 * 2. 成组后，开始派对，共三分钟
 * 3. 失败：检测人手是否包含纸牌，如果有，失败
 *  *          如果星星数量小于3个，失败
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
