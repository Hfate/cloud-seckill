package com.hcq.seckill.service.impl;

import com.hcq.seckill.dto.OrderDto;
import com.hcq.seckill.repository.IOrderRepository;
import com.hcq.seckill.service.IOrderService;
import com.hcq.seckill.service.ISecKillOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOrderRepository orderRepository;

    @Resource
    private ISecKillOrderService secKillOrderService;


    @Override
    public void createOrder(OrderDto orderDto) {

    }
}
