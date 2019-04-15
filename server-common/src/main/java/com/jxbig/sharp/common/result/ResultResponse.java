package com.jxbig.sharp.common.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ResultResponse {

    /**
     * 响应代码
     */
    @Getter @Setter
    private ResultCode code;
    /**
     * 响应信息
     */
    @Getter @Setter
    private String msg;
    /**
     * 响应数据
     */
    @Getter @Setter
    private Object data;
    private ResultResponse(ResultCode code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private ResultResponse(ResultCode code, Object data) {
        this.code = code;
        this.data = data;
    }
    private ResultResponse(ResultCode code) {
        this.code = code;
    }

    public static ResultResponse writeOut(ResultCode code, String msg){
        return new ResultResponse(code, msg);
    }
    public static ResultResponse writeOut(ResultCode code, Object data){
        return new ResultResponse(code, data);
    }
    public static ResultResponse writeOut(ResultCode code){
        return new ResultResponse(code);
    }




}
