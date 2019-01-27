package com.hcq.seckill.feign;

public class KeyGenServiceFallback implements IKeyGenServiceClient {

    @Override
    public String getKey() {
        return "";
    }
}
