package com.winn.aliyun.util;

public class ResultData extends ResponseResult {

    private static final long serialVersionUID = 771399748278455193L;

    public ResultData(String code, String msg) {
        super(code, msg);
    }

    public ResultData(MsgCode msgCode) {
        super(msgCode);
    }

    private Long total;

    private Object data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" + "total=" + total + ", data=" + data + '}';
    }
}
