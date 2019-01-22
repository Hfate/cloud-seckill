package com.hcq.seckill.service.impl;

import com.hcq.seckill.domain.User;
import com.hcq.seckill.exception.GlobalException;
import com.hcq.seckill.repository.IUserRepository;
import com.hcq.seckill.service.IUserService;
import com.hcq.seckill.util.CommonUtil;
import com.hcq.seckill.dto.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.hcq.seckill.constant.GlobalConstant.COOKIE_NAME_TOKEN;
import static com.hcq.seckill.constant.GlobalConstant.MAX_AGE;
import static com.hcq.seckill.result.ErrorCode.PASS_WORD_ERROR;
import static com.hcq.seckill.result.ErrorCode.USERNAME_NOT_EXIST;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserRepository userRepository;


    @Override
    public void login(HttpServletResponse response, UserVo userVo) {
        User user = userRepository.findByUserName(userVo.getUserName());
        if (user == null) {
            throw new GlobalException(USERNAME_NOT_EXIST);
        }
        if (!user.getPassWord().equals(userVo.getPassWord())) {
            throw new GlobalException(PASS_WORD_ERROR);
        }

        //生成cookie
        String token = CommonUtil.getUuid();
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MAX_AGE);
        cookie.setPath("/");

        //增加cookie
        response.addCookie(cookie);
    }

    @Override
    public void reduceAccount(Long id, Long reduceAmount) {
        userRepository.reduceAccount(id, reduceAmount);
    }
}
