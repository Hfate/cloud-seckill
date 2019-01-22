package com.hcq.seckill.web;

import com.hcq.seckill.dto.OrderDto;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class ISecKillOrderController {
    @Resource
    private IOrderService orderService;


    @PostMapping("createOrder")
    public ApiResult createOrder(OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ApiResult.success();
    }

}
