package com.hcq.seckill.rest;


import com.alibaba.fastjson.JSONObject;
import com.hcq.seckill.redis.dto.DecrDTO;
import com.hcq.seckill.redis.dto.GetDTO;
import com.hcq.seckill.redis.dto.SetDTO;
import com.hcq.seckill.redis.key.KeyPrefix;
import com.hcq.seckill.service.IRedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    private IRedisService redisService;


    @PostMapping("set")
    public void set(@RequestBody SetDTO setDTO) {
        redisService.set(setDTO);
    }


    @PostMapping("get")
    public Object get(@RequestBody GetDTO getDTO) {
        return redisService.get(getDTO);
    }


    @PostMapping("decr")
    public Long decr(@RequestBody DecrDTO decrDTO) {
        return redisService.decr(decrDTO);
    }
}
