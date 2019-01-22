package com.hcq.seckill.dto;

import lombok.Data;

@Data
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVO goodsVO;
}
