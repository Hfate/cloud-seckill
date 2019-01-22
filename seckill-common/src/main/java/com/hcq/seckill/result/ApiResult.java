package com.hcq.seckill.result;

import lombok.Data;

@Data
public class ApiResult<T> {
    private int code;
    private String msg;
    private T data;

    public ApiResult() {
    }

    private ApiResult(ErrorCode e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    private ApiResult(T data) {
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }

    public ApiResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResult<T> success() {
        return success(null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> error(ErrorCode errorCode) {
        return new ApiResult<>(errorCode);
    }
}
