package com.hcq.seckill.redis.key;

public class SecKillKey extends BaseKeyPrefix {
    public static SecKillKey SEC_KILL_KEY = new SecKillKey("secKillKey");

    private SecKillKey(String keyPrefix) {
        super(keyPrefix);
    }
}
