package com.hcq.seckill.fegin;

import com.hcq.seckill.model.GoodsDTO;
import com.hcq.seckill.result.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seckill-goods", fallback = GoodsServiceClientFallBack.class)
public interface IGoodsServiceClient {

    @GetMapping("goods/detail/{goodsId}")
    ApiResult getSecKillGoods(@PathVariable("goodsId")Long goodsId);

    @PostMapping("goods/reduceStock")
    ApiResult reduceStock(@RequestParam("goodsId") Long goodsId,@RequestParam("version") Long version);
}
