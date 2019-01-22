package com.hcq.seckillgate;

import com.hcq.seckillgate.filters.AccessFilter;
import com.hcq.seckillgate.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SecKillGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillGateApplication.class, args);
    }


    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}

