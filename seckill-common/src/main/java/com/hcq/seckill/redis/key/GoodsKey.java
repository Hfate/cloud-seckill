package com.hcq.seckill.redis.key;

public class GoodsKey extends BaseKeyPrefix {
    private static final int GOODS_EXPIRE = 3600 * 2;
    public static GoodsKey GOODS_STOCK = new GoodsKey("goodsStock", 0);


    private GoodsKey(String keyPrefix, int expireSecond) {
        super(expireSecond, keyPrefix);
    }

}
