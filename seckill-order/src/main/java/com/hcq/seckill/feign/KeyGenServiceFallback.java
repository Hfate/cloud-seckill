package com.hcq.seckill.feign;

public class KeyGenServiceFallback implements KeyGenServiceClient {

    @Override
    public Long getKey() {
        return 0L;
    }
}
