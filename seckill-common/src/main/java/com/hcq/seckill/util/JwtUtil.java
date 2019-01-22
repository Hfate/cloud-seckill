package com.hcq.seckill.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcq.seckill.model.LoginUser;

import java.util.Date;

import static com.hcq.seckill.constant.GlobalConstant.MAX_AGE;

/**
 * @author hcq
 * @Date 2018/12/20.
 * @Email 549398044@qq.com
 */
public class JwtUtil {
    private static final String USER_INFO = "userInfo";
    private static final String SECRET = "loginUser";

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param userInfo
     * @return 是否正确
     */
    public static boolean verify(String token, String userInfo) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(USER_INFO, userInfo)
                .build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static LoginUser getUserInfo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String userInfo = jwt.getClaim(USER_INFO).asString();
            return JSONObject.parseObject(userInfo, LoginUser.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static boolean isExpire(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().getTime() < System.currentTimeMillis();
    }


    /**
     * 生成签名,5min后过期
     *
     * @param userInfo
     * @return 加密的token
     */
    public static String sign(String userInfo) {
        Date date = new Date(System.currentTimeMillis() + MAX_AGE);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(USER_INFO, userInfo)
                .withExpiresAt(date)
                .sign(algorithm);

    }
}
