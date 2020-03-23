package com.winn.aliyun.util;

import com.winn.aliyun.util.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

public enum MsgCode {
    SYSTEM_SUCCESS("0000", "操作成功"),
    SYSTEM_ERROR("9999", "系统繁忙,请稍后再试..."),



    ;

    MsgCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String toJson() {
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "!\"}";
    }

    public static String getMsgByCode(String code) {
        for (final MsgCode msgCode : MsgCode.values()) {
            if (code.equals(msgCode.getCode())) {
                return msgCode.getMsg();
            }
        }
        return null;
    }

    public static Map<String, Object> messageResult(MsgCode msgCode) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", msgCode.getCode());
        result.put("msg", msgCode.getMsg());
        return result;
    }

    public CustomException toCustomException() throws CustomException {
        throw new CustomException(code, msg);
    }

    private String code;

    private String msg;

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
}
