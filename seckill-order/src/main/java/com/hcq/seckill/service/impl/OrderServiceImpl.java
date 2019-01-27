package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.Order;
import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.feign.IKeyGenServiceClient;
import com.hcq.seckill.model.SecKillDTO;
import com.hcq.seckill.repository.IOrderRepository;
import com.hcq.seckill.service.IOrderService;
import com.hcq.seckill.service.ISecKillOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOrderRepository orderRepository;
    @Resource
    private ISecKillOrderService secKillOrderService;
    @Resource
    private IKeyGenServiceClient keyGenServiceClient;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public SecKillOrder createOrder(SecKillDTO secKillDTO) {
        long goodsId = secKillDTO.getGoodsId();
        Order order = new Order();
        Long id = System.currentTimeMillis();
        System.out.println(id);
        order.setId(id);
        order.setCreateTime(System.currentTimeMillis());
        order.setGoodsId(goodsId);
        order.setUserId(secKillDTO.getUserId());
        order.setGoodsCount(1);
        order.setGoodsName(secKillDTO.getGoodsName());
        order.setGoodsPrice(secKillDTO.getGoodsPrice());
        order.setPayTime(System.currentTimeMillis());
        //写订单
        order = orderRepository.save(order);

        id = System.currentTimeMillis();
        SecKillOrder secKillOrder = SecKillOrder.builder()
                .id(id)
                .goodsId(goodsId)
                .userId(secKillDTO.getUserId())
                .orderId(order.getId())
                .build();
        secKillOrderService.save(secKillOrder);

        return secKillOrder;
    }
}
