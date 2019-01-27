package com.hcq.seckill.rest;


import com.hcq.seckill.model.GoodsDTO;
import com.hcq.seckill.model.PageQueryParams;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.result.ErrorCode;
import com.hcq.seckill.service.IGoodsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "商品")
@RequestMapping("goods")
public class GoodsController {
    @Resource
    private IGoodsService goodsService;


    @GetMapping("list")
    public ApiResult getPage(PageQueryParams pageQueryParams) {
        return ApiResult.success(goodsService.getPage(pageQueryParams));
    }

    @GetMapping("/detail/{goodsId}")
    public ApiResult detail(@PathVariable("goodsId") long goodsId) {
        GoodsDTO goods = goodsService.getSecKillGoods(goodsId);
        return ApiResult.success(goods);
    }

    @PostMapping("reduceStock")
    public ApiResult reduceStock(@RequestParam("goodsId") Long goodsId, @RequestParam("version") Long version) {
        boolean flag = goodsService.reduceStock(goodsId, version);
        if (flag) {
            return ApiResult.success();
        }
        return ApiResult.error(ErrorCode.UPDATE_STOCK_FAIL);
    }

}
