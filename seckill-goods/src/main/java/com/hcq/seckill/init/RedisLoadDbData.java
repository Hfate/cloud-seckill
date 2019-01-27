package com.hcq.seckill.init;


import com.hcq.seckill.domain.SecKillGoods;
import com.hcq.seckill.feign.IRedisServiceClient;
import com.hcq.seckill.redis.dto.SetDTO;
import com.hcq.seckill.redis.key.GoodsKey;
import com.hcq.seckill.service.ISecKillGoodsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.hcq.seckill.constant.GlobalConstant.GOODS_OVER_MAP;

@Component
public class RedisLoadDbData implements InitializingBean {
    @Resource
    private ISecKillGoodsService secKillGoodsService;
    @Resource
    private IRedisServiceClient redisServiceClient;

    @Override
    public void afterPropertiesSet() {
        //获取所有参与秒杀的商品
        List<SecKillGoods> secKillGoods = secKillGoodsService.getAll();
        if (secKillGoods.size() == 0) {
            return;
        }

        //redis 存储商品的库存
        for (SecKillGoods secKillGoods1 : secKillGoods) {
            SetDTO setDTO = new SetDTO();
            setDTO.setKey("" + secKillGoods1.getGoodsId());
            setDTO.setPrefix(GoodsKey.GOODS_STOCK.getKeyPrefix());
            setDTO.setExpireSeconds(GoodsKey.GOODS_STOCK.expireSeconds());
            setDTO.setObject(secKillGoods1.getStockCount());
            redisServiceClient.set(setDTO);
            GOODS_OVER_MAP.put(secKillGoods1.getGoodsId(), false);
        }
    }
}
