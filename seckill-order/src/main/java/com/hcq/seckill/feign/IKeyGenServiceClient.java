package com.hcq.seckill.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "seckill-keygen",fallback = KeyGenServiceFallback.class)
public interface IKeyGenServiceClient {
    @GetMapping("/keygen/generateKey")
    String getKey();
}
