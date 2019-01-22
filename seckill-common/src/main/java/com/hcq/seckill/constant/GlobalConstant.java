package com.hcq.seckill.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hcq
 * @Date 2018/4/26.
 * @Email 549398044@qq.com
 * 常用常量
 */
public class GlobalConstant {
    /**
     * TOKEN
     */
    public final static String COOKIE_NAME_TOKEN = "token";

    public final static int MAX_AGE=86000;

    /**
     * 商品是否卖完，内存标记
     */
    public final static Map<Long, Boolean> GOODS_OVER_MAP = new ConcurrentHashMap<>();
}
