package com.hcq.seckill.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hcq
 * @Date 2018/11/6.
 * @Email 549398044@qq.com
 */
@ApiModel
@Data
public class PageQueryParams implements Serializable {
    @ApiModelProperty(value = "页码数")
    private int pageIndex;
    @ApiModelProperty(value = "每页条数")
    private int pageSize;
}
