package com.hcq.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecKillDTO implements Serializable {
    private Long userId;
    private Long goodsId;
    private String goodsName;
    private Long goodsPrice;
}
