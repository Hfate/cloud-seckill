package com.hcq.seckill.web;


import com.hcq.seckill.model.PageQueryParams;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.IGoodsService;
import com.hcq.seckill.dto.GoodsVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "商品")
@RequestMapping("admin/goods")
public class GoodsController {
    @Resource
    private IGoodsService goodsService;


    @GetMapping("list")
    public ApiResult getPage(PageQueryParams pageQueryParams) {
        return ApiResult.success(goodsService.getPage(pageQueryParams));
    }

    @GetMapping("/detail/{goodsId}")
    public ApiResult detail(@PathVariable("goodsId") long goodsId) {
        GoodsVO goods = goodsService.getSecKillGoods(goodsId);
        return ApiResult.success(goods);
    }

}
