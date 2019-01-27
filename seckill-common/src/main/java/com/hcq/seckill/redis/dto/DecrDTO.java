package com.hcq.seckill.redis.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DecrDTO implements Serializable {
    private String prefix;
    private String key;
}
