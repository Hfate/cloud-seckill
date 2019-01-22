package com.hcq.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SecKillServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillServerApplication.class, args);
    }

}

