package com.jxbig.sharp.common.result;

public enum  ResultCode {

    /**
     * 请求成功。一般用于GET与POST请求
     */
    OK(200),
    /**
     * 客户端请求的语法错误，服务器无法理解
     */
    BAD_REQUEST(400),
    /**
     * 请求要求用户的身份认证
     */
    UNAUTHORIZED(401),
    /**
     * 服务器内部错误，无法完成请求
     */
    INTERNAL_SERVER_ERROR(500);

    private Integer code;
    ResultCode(Integer code) {
        this.code = code;
    }
}
