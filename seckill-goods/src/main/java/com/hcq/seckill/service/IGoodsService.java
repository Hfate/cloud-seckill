package com.hcq.seckill.service;

import com.hcq.seckill.model.GoodsDTO;
import com.hcq.seckill.model.PageQueryParams;
import com.hcq.seckill.model.Pager;

public interface IGoodsService {
    /**
     * 获取商品分页
     *
     * @param pageQueryParams
     * @return
     */
    Pager getPage(PageQueryParams pageQueryParams);

    GoodsDTO getSecKillGoods(long goodsId);

    boolean reduceStock(Long id, Long version);
}
