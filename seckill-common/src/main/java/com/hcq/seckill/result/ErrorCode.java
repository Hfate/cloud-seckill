package com.hcq.seckill.result;

public enum ErrorCode {
    OK(0, "ok"),
    NOT_LOGIN(500001, "未登录"),
    USERNAME_NOT_EXIST(500002, "用户名不存在"),
    PASS_WORD_ERROR(500003, "密码错误"),
    STOCK_NOT_ENOUGH(500004, "库存不足,秒杀失败"),
    ORDER_REPEAT(500006, "重复秒杀");


    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
