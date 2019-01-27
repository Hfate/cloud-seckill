package com.hcq.seckill.rest;

import com.hcq.seckill.model.SecKillDTO;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class ISecKillOrderController {
    @Resource
    private IOrderService orderService;


    @PostMapping("createOrder")
    public ApiResult createOrder(@RequestBody SecKillDTO secKillDTO) {
        return ApiResult.success(orderService.createOrder(secKillDTO));
    }

}
