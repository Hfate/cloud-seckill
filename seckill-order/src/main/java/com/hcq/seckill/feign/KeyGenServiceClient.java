package com.hcq.seckill.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(fallback = KeyGenServiceFallback.class)
public interface KeyGenServiceClient {
    @GetMapping("/keygen")
    Long getKey();
}
