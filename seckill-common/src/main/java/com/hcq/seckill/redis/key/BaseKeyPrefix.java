package com.hcq.seckill.redis.key;

public abstract class BaseKeyPrefix implements KeyPrefix {
    private int expireSecond;
    private String keyPrefix;


    public BaseKeyPrefix(String keyPrefix) {
        this(0, keyPrefix);
    }

    public BaseKeyPrefix(int expireSecond, String keyPrefix) {
        this.expireSecond = expireSecond;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public int expireSeconds() {
        // 0 代表永不过期
        return expireSecond;
    }

    @Override
    public String getKeyPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + keyPrefix;
    }
}
