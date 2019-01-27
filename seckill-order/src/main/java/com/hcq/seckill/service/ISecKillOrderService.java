package com.hcq.seckill.service;

import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.dto.SecKillOrderDto;

/**
 * @author hcq
 */
public interface ISecKillOrderService {


    SecKillOrder findByGoodsIdAndUserId(long userId, long goodsId);

    void save(SecKillOrder secKillOrder);
}
