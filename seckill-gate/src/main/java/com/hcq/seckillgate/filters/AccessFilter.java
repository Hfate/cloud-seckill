package com.hcq.seckillgate.filters;

import com.hcq.seckill.model.LoginUser;
import com.hcq.seckill.result.ApiResult;
import com.hcq.seckill.util.HttpUtil;
import com.hcq.seckill.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hcq.seckill.constant.GlobalConstant.COOKIE_NAME_TOKEN;
import static com.hcq.seckill.result.ErrorCode.NOT_LOGIN;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        //简单检测非登录接口，是否登录
        if (!request.getRequestURI().contains("login")) {
            checkLogin(response, request);
        }
        return null;
    }


    private void checkLogin(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String pcToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME_TOKEN)) {
                    pcToken = cookie.getValue();
                    break;
                }
            }
        }
        String mobileToken = request.getParameter(COOKIE_NAME_TOKEN);
        String token = StringUtils.isBlank(pcToken) ? mobileToken : pcToken;

        if (isLogin(token)) {
            if (JwtUtil.isExpire(token)) {
                HttpUtil.renderJSON(response, ApiResult.error(NOT_LOGIN));
            }
        } else {
            HttpUtil.renderJSON(response, ApiResult.error(NOT_LOGIN));
        }
    }

    private boolean isLogin(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        LoginUser loginUser = JwtUtil.getUserInfo(token);
        return loginUser != null;
    }
}
