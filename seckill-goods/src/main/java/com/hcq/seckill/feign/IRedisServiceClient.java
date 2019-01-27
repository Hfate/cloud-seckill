package com.hcq.seckill.feign;

import com.hcq.seckill.redis.dto.SetDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "seckill-redis", fallback = RedisClientFallBack.class)
public interface IRedisServiceClient {
    @PostMapping(value = "redis/set")
    void set(@RequestBody SetDTO setDTO);
}
