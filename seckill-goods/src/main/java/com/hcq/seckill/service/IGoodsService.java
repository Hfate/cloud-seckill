package com.hcq.seckill.service;

import com.hcq.seckill.model.PageQueryParams;
import com.hcq.seckill.model.Pager;
import com.hcq.seckill.dto.GoodsVO;

public interface IGoodsService {
    /**
     * 获取商品分页
     *
     * @param pageQueryParams
     * @return
     */
    Pager getPage(PageQueryParams pageQueryParams);

    GoodsVO getSecKillGoods(long goodsId);

    boolean reduceStock(Long id);
}
