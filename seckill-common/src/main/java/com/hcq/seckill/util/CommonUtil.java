package com.hcq.seckill.util;

import java.util.UUID;

public class CommonUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
