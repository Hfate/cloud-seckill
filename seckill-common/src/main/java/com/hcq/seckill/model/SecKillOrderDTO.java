package com.hcq.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecKillOrderDTO implements Serializable {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
