package com.hcq.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SecKillKeygenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillKeygenApplication.class, args);
    }

}

