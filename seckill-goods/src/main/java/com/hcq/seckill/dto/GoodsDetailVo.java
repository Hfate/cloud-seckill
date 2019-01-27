package com.hcq.seckill.dto;

import com.hcq.seckill.model.GoodsDTO;
import lombok.Data;

@Data
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsDTO goodsDTO;
}
