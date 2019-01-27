package com.hcq.seckill.redis.key;

public interface KeyPrefix {

    int expireSeconds();

    String getKeyPrefix();
}
