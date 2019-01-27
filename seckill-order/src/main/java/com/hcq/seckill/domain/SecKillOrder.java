package com.hcq.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_sec_kill_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecKillOrder implements Serializable {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "goods_id")
    private Long goodsId;

}
