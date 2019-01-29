package com.cmcc.spring.exception;

public class BizException extends RuntimeException {

    protected int code;

    public BizException() {
        super();
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Throwable arg0) {
        super(arg0);
    }

    public int getCode() {
        return code;
    }
}
