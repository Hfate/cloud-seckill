package com.hcq.seckill.fegin;

import com.hcq.seckill.model.SecKillDTO;
import com.hcq.seckill.model.SecKillOrderDTO;

public class OrderServiceClientFallBack implements IOrderServiceClient {
    @Override
    public SecKillOrderDTO createOrder(SecKillDTO secKillDTO) {
        return null;
    }
}
