package com.hcq.seckill.fegin;

import com.alibaba.fastjson.JSONObject;
import com.hcq.seckill.exception.GlobalException;
import com.hcq.seckill.redis.dto.DecrDTO;
import com.hcq.seckill.redis.dto.GetDTO;
import com.hcq.seckill.redis.dto.SetDTO;
import lombok.extern.slf4j.Slf4j;

import static com.hcq.seckill.result.ErrorCode.REDIS_SET_ERROR;

@Slf4j
public class RedisClientFallBack implements IRedisServiceClient {

    @Override
    public void set(SetDTO setDTO) {
        log.error("redis set 失败，参数{}", JSONObject.toJSONString(setDTO));
        throw new GlobalException(REDIS_SET_ERROR);
    }


    @Override
    public Object get(GetDTO getDTO) {
        return null;
    }

    @Override
    public long decr(DecrDTO decrDTO) {
        return 0;
    }
}
