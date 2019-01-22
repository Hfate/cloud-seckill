package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.dto.SecKillOrderDto;
import com.hcq.seckill.feign.KeyGenServiceClient;
import com.hcq.seckill.repository.ISecKillOrderRepository;
import com.hcq.seckill.service.ISecKillOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecKillOrderServiceImpl implements ISecKillOrderService {
    @Resource
    private ISecKillOrderRepository secKillOrderRepository;
    @Resource
    private KeyGenServiceClient keyGenServiceClient;

    @Override
    public SecKillOrder findByGoodsIdAndUserId(long userId, long goodsId) {
        return secKillOrderRepository.findByGoodsIdAndUserId(userId, goodsId);
    }

    @Override
    public void createSecKillOrder(SecKillOrderDto secKillOrderDto) {
        SecKillOrder secKillOrder = SecKillOrder.builder()
                .id(keyGenServiceClient.getKey())
                .goodsId(secKillOrderDto.getGoodsId())
                .userId(secKillOrderDto.getUserId())
                .orderId(secKillOrderDto.getOrderId())
                .build();
        secKillOrderRepository.save(secKillOrder);
    }
}
