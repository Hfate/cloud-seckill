package com.hcq.seckill.redis.key;

public class UserKey extends BaseKeyPrefix {
    private static final int TOKEN_EXPIRE = 3600 * 2;
    public static UserKey TOKEN = new UserKey("token", TOKEN_EXPIRE);

    private UserKey(String keyPrefix, int expireSecond) {
        super(expireSecond, keyPrefix);
    }

}
