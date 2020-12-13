package com.sofosofi.identsystemwechat.common;

public class CustomException extends RuntimeException {

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.code = 500;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
