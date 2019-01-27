package com.hcq.seckill.service;

import com.hcq.seckill.model.GoodsDTO;

public interface ITradeService {
    void secKill(long goodsId, long userId);

    long getSecKillOrderId(long goodsId, long userId);

    void doSecKill(GoodsDTO secKillGoods, Long userId);
}
