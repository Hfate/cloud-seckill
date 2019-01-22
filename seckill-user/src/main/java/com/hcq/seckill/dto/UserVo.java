package com.hcq.seckill.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private String userName;
    private String passWord;
}
