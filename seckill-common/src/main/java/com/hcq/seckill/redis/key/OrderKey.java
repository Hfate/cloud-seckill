package com.hcq.seckill.redis.key;

public class OrderKey extends BaseKeyPrefix {
    public static OrderKey GOODS_STOCK_OVER = new OrderKey("goodsStockOver");
    public static OrderKey SEC_KILL_SUCCESS = new OrderKey("secKillSuccess");
    private OrderKey(String keyPrefix) {
        super(keyPrefix);
    }
}
