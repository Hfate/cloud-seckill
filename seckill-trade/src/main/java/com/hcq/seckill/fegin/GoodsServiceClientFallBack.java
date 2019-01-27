package com.hcq.seckill.fegin;


import com.hcq.seckill.result.ApiResult;

public class GoodsServiceClientFallBack implements IGoodsServiceClient {

    @Override
    public ApiResult getSecKillGoods(Long goodsId) {
        return null;
    }

    @Override
    public ApiResult reduceStock(Long goodsId, Long version) {
        return null;
    }
}
