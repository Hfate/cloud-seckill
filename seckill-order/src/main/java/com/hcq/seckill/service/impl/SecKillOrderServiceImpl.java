package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.dto.SecKillOrderDto;
import com.hcq.seckill.feign.IKeyGenServiceClient;
import com.hcq.seckill.repository.ISecKillOrderRepository;
import com.hcq.seckill.service.ISecKillOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecKillOrderServiceImpl implements ISecKillOrderService {
    @Resource
    private ISecKillOrderRepository secKillOrderRepository;

    @Override
    public SecKillOrder findByGoodsIdAndUserId(long userId, long goodsId) {
        return secKillOrderRepository.findByGoodsIdAndUserId(userId, goodsId);
    }

    @Override
    public void save(SecKillOrder secKillOrder) {
        secKillOrderRepository.save(secKillOrder);
    }
}
