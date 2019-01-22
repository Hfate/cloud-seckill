package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.Goods;
import com.hcq.seckill.domain.SecKillGoods;
import com.hcq.seckill.model.PageQueryParams;
import com.hcq.seckill.model.Pager;
import com.hcq.seckill.repository.IGoodsRepository;
import com.hcq.seckill.service.IGoodsService;
import com.hcq.seckill.service.ISecKillGoodsService;
import com.hcq.seckill.dto.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Resource
    private IGoodsRepository goodsRepository;
    @Resource
    private ISecKillGoodsService secKillGoodsService;

    @Override
    public Pager getPage(PageQueryParams pageQueryParams) {
        PageRequest pageRequest = new PageRequest(pageQueryParams.getPageIndex() - 1, pageQueryParams.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
        Page<Goods> page = goodsRepository.findAll(pageRequest);
        List<GoodsVO> goodsVOS = new ArrayList<>();
        GoodsVO goodsVO;
        SecKillGoods secKillGoods;
        for (Goods goods : page) {
            goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            secKillGoods = secKillGoodsService.findByGoodsId(goods.getId());
            if (secKillGoods != null) {
                goodsVO.setEndTime(secKillGoods.getEndTime());
                goodsVO.setStartTime(secKillGoods.getStartTime());
                goodsVO.setSecKillPrice(secKillGoods.getSecKillPrice());
                goodsVO.setStockCount(secKillGoods.getStockCount());
            }
            goodsVOS.add(goodsVO);
        }
        Pager<GoodsVO> pager = new Pager<>();
        pager.setList(goodsVOS);
        pager.setTotal(page.getTotalElements());
        pager.setPages(page.getTotalPages());
        return pager;
    }

    @Override
    public GoodsVO getSecKillGoods(long goodsId) {
        Goods goods = goodsRepository.findOne(goodsId);
        SecKillGoods secKillGoods = secKillGoodsService.findByGoodsId(goodsId);
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setId(goods.getId());
        goodsVO.setGoodsImg(goods.getGoodsImg());
        goodsVO.setGoodsName(goods.getGoodsName());
        goodsVO.setGoodsPrice(goods.getGoodsPrice());
        goodsVO.setGoodsStock(goods.getGoodsStock());
        goodsVO.setGoodsTitle(goods.getGoodsTitle());

        //秒杀进行中
        int status = 0;
        int remainSeconds = 0;
        if (secKillGoods != null) {
            goodsVO.setEndTime(secKillGoods.getEndTime());
            goodsVO.setStartTime(secKillGoods.getStartTime());
            goodsVO.setSecKillPrice(secKillGoods.getSecKillPrice());
            goodsVO.setStockCount(secKillGoods.getStockCount());
            goodsVO.setVersion(secKillGoods.getVersion());
            long now = System.currentTimeMillis();

            if (now < goodsVO.getStartTime()) {
                //秒杀还没开始，倒计时
                remainSeconds = (int) ((goodsVO.getStartTime() - now) / 1000);
            } else if (now > goodsVO.getEndTime()) {
                //秒杀已经结束
                status = 2;
                remainSeconds = -1;
            } else {
                //秒杀进行中
                status = 1;
            }
        }
        goodsVO.setStatus(status);
        goodsVO.setRemainSeconds(remainSeconds);
        return goodsVO;
    }

    @Override
    public boolean reduceStock(Long id) {
        return goodsRepository.reduceStock(id) > 0;
    }
}
