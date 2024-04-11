package org.oj.exception;

import org.oj.constant.ResCode;

/**
 * 主动抛出的异常，带有msg
 *
 * @author DH
 * @create 2022-06-14
 */
public class ActiveException extends Exception {
    private final int code;
    private final String msg;

    public ActiveException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ActiveException(String msg) {
        super(msg);
        this.code = ResCode.BAD;
        this.msg = msg;
    }

    public ActiveException(int code) {
        super("");
        this.code = code;
        this.msg = null;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
