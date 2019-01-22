package com.hcq.seckill.repository;


import com.hcq.seckill.domain.SecKillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISecKillOrderRepository extends JpaRepository<SecKillOrder, Long> {

    /**
     * 根据用户id和商品id 获取秒杀订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    SecKillOrder findByGoodsIdAndUserId(long userId, long goodsId);
}
