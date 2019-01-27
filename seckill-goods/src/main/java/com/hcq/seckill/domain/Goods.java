package com.hcq.seckill.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable {
    @Id
    private Long id;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_title")
    private String goodsTitle;
    @Column(name = "goods_img")
    private String goodsImg;
    @Column(name = "goods_price")
    private Long goodsPrice;
    @Column(name = "goods_stock")
    private Integer goodsStock;
}
