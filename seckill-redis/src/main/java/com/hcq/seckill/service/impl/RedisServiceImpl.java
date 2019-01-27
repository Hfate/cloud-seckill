package com.hcq.seckill.service.impl;


import com.hcq.seckill.redis.dto.DecrDTO;
import com.hcq.seckill.redis.dto.GetDTO;
import com.hcq.seckill.redis.dto.SetDTO;
import com.hcq.seckill.service.IRedisService;
import com.hcq.seckill.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements IRedisService {
    @Resource
    private RedisUtil redisUtil;


    @Override
    public Object get(GetDTO getDTO) {
        String realKey = getDTO.getPrefix() + getDTO.getKey();
        return redisUtil.get(realKey);
    }

    @Override
    public void set(SetDTO setDTO) {
        String realKey = setDTO.getPrefix() + setDTO.getKey();
        redisUtil.set(realKey, setDTO.getObject(), setDTO.getExpireSeconds());
    }

    @Override
    public long decr(DecrDTO decrDTO) {
        String realKey = decrDTO.getPrefix() + decrDTO.getKey();
        return redisUtil.decr(realKey, 1);
    }
}
