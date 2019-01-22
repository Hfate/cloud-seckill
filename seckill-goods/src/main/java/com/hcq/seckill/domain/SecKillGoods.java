package com.hcq.seckill.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_sec_kill_goods")
public class SecKillGoods implements Serializable {
    @Id
    private Long id;
    @Column(name = "goods_id")
    private Long goodsId;
    @Column(name = "stock_count")
    private Long stockCount;
    @Column(name = "start_time")
    private Long startTime;
    @Column(name = "end_time")
    private Long endTime;
    @Column(name = "sec_kill_price")
    private Double secKillPrice;
    @Column(name = "version")
    private Long version;
}
