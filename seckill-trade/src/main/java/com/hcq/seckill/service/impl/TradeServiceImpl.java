package com.hcq.seckill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hcq.seckill.exception.GlobalException;
import com.hcq.seckill.fegin.IGoodsServiceClient;
import com.hcq.seckill.fegin.IOrderServiceClient;
import com.hcq.seckill.fegin.IRedisServiceClient;
import com.hcq.seckill.kafka.SecKillMessage;
import com.hcq.seckill.model.GoodsDTO;
import com.hcq.seckill.model.SecKillDTO;
import com.hcq.seckill.model.SecKillOrderDTO;
import com.hcq.seckill.redis.dto.DecrDTO;
import com.hcq.seckill.redis.dto.GetDTO;
import com.hcq.seckill.redis.dto.SetDTO;
import com.hcq.seckill.redis.key.GoodsKey;
import com.hcq.seckill.redis.key.OrderKey;
import com.hcq.seckill.redis.key.SecKillKey;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.result.ErrorCode;
import com.hcq.seckill.service.ITradeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.hcq.seckill.constant.GlobalConstant.GOODS_OVER_MAP;
import static com.hcq.seckill.result.ErrorCode.STOCK_NOT_ENOUGH;

@Service
public class TradeServiceImpl implements ITradeService {
    @Resource
    private IRedisServiceClient redisServiceClient;
    @Resource
    private KafkaTemplate kafkaTemplate;
    @Resource
    private IGoodsServiceClient goodsServiceClient;
    @Resource
    private IOrderServiceClient orderServiceClient;

    @Override
    public void secKill(long goodsId, long userId) {
        //已卖完,直接返回 ，减少redis 的访问
//        if (GOODS_OVER_MAP.get(goodsId)) {
//            throw new GlobalException(STOCK_NOT_ENOUGH);
//        }
        //预减库存
        DecrDTO decrDTO = new DecrDTO();
        decrDTO.setKey("" + goodsId);
        decrDTO.setPrefix(GoodsKey.GOODS_STOCK.getKeyPrefix());
        long stock = redisServiceClient.decr(decrDTO);
        if (stock < 0) {
            GOODS_OVER_MAP.put(goodsId, true);
            throw new GlobalException(STOCK_NOT_ENOUGH);
        }

        //判断是否秒杀到了
        GetDTO getDTO = new GetDTO();
        getDTO.setKey(userId + "_" + goodsId);
        getDTO.setPrefix(SecKillKey.SEC_KILL_KEY.getKeyPrefix());
        Object order = redisServiceClient.get(getDTO);
        if (order != null) {
            throw new GlobalException(ErrorCode.ORDER_REPEAT);
        }

        //进入秒杀队列

        SecKillMessage secKillMessage = new SecKillMessage(goodsId, userId);
        //入队
        kafkaTemplate.send("seckill", JSONObject.toJSONString(secKillMessage));
    }

    @Override
    public long getSecKillOrderId(long goodsId, long userId) {
        return 0;
    }


    @Override
    public void doSecKill(GoodsDTO secKillGoods, Long userId) {
        ApiResult flag = goodsServiceClient.reduceStock(secKillGoods.getId(), secKillGoods.getVersion());
        SetDTO setDTO = new SetDTO();
        if (flag.isSuccess()) {
            SecKillDTO secKillDTO = new SecKillDTO();
            secKillDTO.setGoodsId(secKillGoods.getId());
            secKillDTO.setUserId(userId);
            secKillDTO.setGoodsName(secKillGoods.getGoodsName());
            secKillDTO.setGoodsPrice(secKillGoods.getGoodsPrice());
            SecKillOrderDTO secKillOrder = orderServiceClient.createOrder(secKillDTO);

            setDTO.setObject(JSONObject.toJSONString(secKillOrder));
            setDTO.setPrefix(SecKillKey.SEC_KILL_KEY.getKeyPrefix());
            setDTO.setKey(userId + "_" + secKillGoods.getId());
            redisServiceClient.set(setDTO);
        } else {
            setDTO.setObject(true);
            setDTO.setPrefix(OrderKey.GOODS_STOCK_OVER.getKeyPrefix());
            setDTO.setKey("" + secKillGoods.getId());
            //减库存失败
            //商品卖超，存储redis
            redisServiceClient.set(setDTO);
        }
    }
}
