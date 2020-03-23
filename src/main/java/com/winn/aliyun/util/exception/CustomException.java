package com.winn.aliyun.util.exception;

import com.winn.aliyun.util.MsgCode;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -5874694541983244150L;

    private String code;

    private String msg;

    public CustomException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(MsgCode msgCode) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }



    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
