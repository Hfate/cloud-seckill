package com.hcq.seckill.service;

import com.hcq.seckill.domain.SecKillGoods;
import com.hcq.seckill.dto.GoodsVO;

import java.util.List;

public interface ISecKillGoodsService {
    /**
     * 获取所有的秒杀商品
     *
     * @return
     */
    List<SecKillGoods> getAll();

    /**
     * 根据商品id  获取秒杀商品
     *
     * @param id
     * @return
     */
    SecKillGoods findByGoodsId(long id);

    /**
     * 减库存
     *
     * @param goodsVO
     * @return
     */
    boolean reduceStock(GoodsVO goodsVO);
}
