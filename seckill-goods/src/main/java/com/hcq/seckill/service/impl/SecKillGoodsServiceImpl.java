package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.SecKillGoods;
import com.hcq.seckill.repository.ISecKillGoodsRepository;
import com.hcq.seckill.service.IGoodsService;
import com.hcq.seckill.service.ISecKillGoodsService;
import com.hcq.seckill.dto.GoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SecKillGoodsServiceImpl implements ISecKillGoodsService {
    @Resource
    private ISecKillGoodsRepository secKillGoodsRepository;
    @Resource
    private IGoodsService goodsService;

    @Override
    public List<SecKillGoods> getAll() {
        return secKillGoodsRepository.findAll();
    }

    @Override
    public SecKillGoods findByGoodsId(long id) {
        return secKillGoodsRepository.findByGoodsId(id);
    }

    @Override
    public boolean reduceStock(GoodsVO goodsVO) {
        boolean flag = secKillGoodsRepository.reduceStock(goodsVO.getId(), goodsVO.getVersion()) > 0;
        if (flag) {
            flag = goodsService.reduceStock(goodsVO.getId());
        }
        return flag;
    }
}
