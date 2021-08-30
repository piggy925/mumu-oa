package com.example.mumuoa.exception;

import lombok.Data;

@Data
public class MumuoaException extends RuntimeException {
    private String msg;
    private int code = 500;

    public MumuoaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MumuoaException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MumuoaException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public MumuoaException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}