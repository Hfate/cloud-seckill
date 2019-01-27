package com.hcq.seckill.redis.dto;

import com.hcq.seckill.redis.key.KeyPrefix;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetDTO implements Serializable {
    private String prefix;
    private String key;
}
