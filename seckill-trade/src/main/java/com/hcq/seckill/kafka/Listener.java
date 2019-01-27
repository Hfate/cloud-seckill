package com.hcq.seckill.kafka;

import com.alibaba.fastjson.JSONObject;
import com.hcq.seckill.fegin.IGoodsServiceClient;
import com.hcq.seckill.model.GoodsDTO;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.ITradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Optional;

@Slf4j
@Component
public class Listener {
    @Resource
    private ITradeService tradeService;
    @Resource
    private IGoodsServiceClient goodsServiceClient;

    @KafkaListener(topics = {"seckill"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object object = kafkaMessage.get();
            SecKillMessage secKillMessage = JSONObject.parseObject(object.toString(), SecKillMessage.class);
            ApiResult result = goodsServiceClient.getSecKillGoods(secKillMessage.getGoodsId());
            LinkedHashMap map = (LinkedHashMap) result.getData();
            GoodsDTO goodsDTO = JSONObject.parseObject(JSONObject.toJSONString(map), GoodsDTO.class);
            if (goodsDTO.getStockCount() <= 0) {
                return;
            }
            tradeService.doSecKill(goodsDTO, secKillMessage.getUserId());
        }

    }
}
