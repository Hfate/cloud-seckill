package com.hcq.seckill.repository;


import com.hcq.seckill.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IGoodsRepository extends JpaRepository<Goods, Long> {


    @Transactional
    @Modifying
    @Query(value = "update t_goods set goods_stock=goods_stock-1 where id =?1", nativeQuery = true)
    int reduceStock(Long id);
}
