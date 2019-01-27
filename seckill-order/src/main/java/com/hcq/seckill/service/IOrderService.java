package com.hcq.seckill.service;

import com.hcq.seckill.domain.SecKillOrder;
import com.hcq.seckill.model.SecKillDTO;

public interface IOrderService {

    SecKillOrder createOrder(SecKillDTO orderDto);
}
