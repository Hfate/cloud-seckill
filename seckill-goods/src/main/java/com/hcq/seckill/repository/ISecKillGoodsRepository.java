package com.hcq.seckill.repository;

import com.hcq.seckill.domain.SecKillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ISecKillGoodsRepository extends JpaRepository<SecKillGoods, Long> {
    SecKillGoods findByGoodsId(long id);

    @Modifying
    @Query(value = "update t_sec_kill_goods set stock_count=stock_count-1 where goods_id =?1 and version=?2", nativeQuery = true)
    int reduceStock(long id, long version);
}
