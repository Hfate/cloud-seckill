package com.hcq.seckill.fegin;

import com.hcq.seckill.model.SecKillDTO;
import com.hcq.seckill.model.SecKillOrderDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "seckill-order", fallback = GoodsServiceClientFallBack.class)
public interface IOrderServiceClient {

    @PostMapping("order/createOrder")
    SecKillOrderDTO createOrder(@RequestBody SecKillDTO secKillDTO);
}
