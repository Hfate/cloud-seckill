package com.hcq.seckill.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "goods_id")
    private Long goodsId;
    @Column(name = "delivery_addr_id")
    private Long deliveryAddrId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_count")
    private Integer goodsCount;
    @Column(name = "goods_price")
    private Long goodsPrice;
    @Column(name = "order_channel")
    private Integer orderChannel;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "pay_time")
    private Long payTime;

}
