package com.hcq.seckill.service;

import com.hcq.seckill.dto.UserVo;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    void login(HttpServletResponse response, UserVo userVo);

    void reduceAccount(Long id ,Long reduceAmount);
}
