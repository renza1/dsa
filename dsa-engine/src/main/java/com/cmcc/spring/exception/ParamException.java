package com.cmcc.spring.exception;

public class ParamException extends RuntimeException {

    protected int code;

    public ParamException() {
        super();
    }

    public ParamException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ParamException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ParamException(String msg) {
        super(msg);
    }

    public ParamException(Throwable arg0) {
        super(arg0);
    }

    public int getCode() {
        return code;
    }
}
