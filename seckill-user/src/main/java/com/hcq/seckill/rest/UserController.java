package com.hcq.seckill.rest;


import com.alibaba.fastjson.JSONObject;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.service.IUserService;
import com.hcq.seckill.dto.UserVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
@Slf4j
@Api(tags = "用户")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("login")
    public ApiResult login(UserVo userVo, HttpServletResponse response) {
        log.info("login params {} ", JSONObject.toJSONString(userVo));
        userService.login(response, userVo);
        return ApiResult.success();
    }

}
