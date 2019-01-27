package com.hcq.seckill.rest;


import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.ITradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("trade")
@Api(tags = "交易")
public class TradeController {
    @Resource
    private ITradeService tradeService;

    @PostMapping("secKill")
    @ApiOperation("秒杀")
    public ApiResult secKill(long goodsId, long userId) {
        tradeService.secKill(goodsId, userId);
        return ApiResult.success();
    }

    @PostMapping("result")
    public ApiResult result(long goodsId, long userId) {
        long orderId = tradeService.getSecKillOrderId(goodsId, userId);
        return ApiResult.success(orderId);
    }
}
