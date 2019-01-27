package com.hcq.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsDTO implements Serializable {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private Long goodsPrice;
    private Integer goodsStock;

    /**
     * 秒杀价
     */
    private Double secKillPrice;
    /**
     * 库存
     */
    private Long stockCount;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 0  待开始  1 进行中 2 已结束
     */
    private Integer status;
    /**
     * 倒计时
     */
    private Integer remainSeconds;

    private Long version=1L;
}
