package com.winn.aliyun.util;

import java.io.Serializable;
import java.util.Map;

public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -1699569617465561820L;

    private String code;

    private String msg;

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(MsgCode msgCode) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseResult{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
