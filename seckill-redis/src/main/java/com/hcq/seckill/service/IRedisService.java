package com.hcq.seckill.service;


import com.hcq.seckill.redis.dto.DecrDTO;
import com.hcq.seckill.redis.dto.GetDTO;
import com.hcq.seckill.redis.dto.SetDTO;

public interface IRedisService {

    /**
     * 获取
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    Object get(GetDTO getDTO);


    /**
     * 存
     *
     * @param keyPrefix
     * @param key
     * @param object
     * @return
     */
    void set(SetDTO setDTO);

    /**
     * 减
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    long decr(DecrDTO decrDTO);
}
