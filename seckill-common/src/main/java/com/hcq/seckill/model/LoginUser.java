package com.hcq.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private Long id;
    private String userName;
}
