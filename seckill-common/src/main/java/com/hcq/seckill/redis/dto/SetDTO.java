package com.hcq.seckill.redis.dto;

import com.hcq.seckill.redis.key.KeyPrefix;
import lombok.Data;

import java.io.Serializable;

@Data
public class SetDTO implements Serializable {
    private String prefix;
    private String key;
    private Object object;
    private int expireSeconds;
}
