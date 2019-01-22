package com.hcq.seckill.service;

import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.dto.SecKillOrderDto;

/**
 * @author hcq
 */
public interface ISecKillOrderService {
    /**
     * 创建订单
     *
     * @param orderVo
     * @return
     */
    void createSecKillOrder(SecKillOrderDto orderVo);

    SecKillOrder findByGoodsIdAndUserId(long userId, long goodsId);
}
