package com.hcq.seckill.exception;


import com.hcq.seckill.result.ErrorCode;

/**
 * @author hcq
 * @Date 2018/11/27.
 * @Email 549398044@qq.com
 */
public class GlobalException extends RuntimeException {
    private ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    private GlobalException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
