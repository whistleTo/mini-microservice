package com.psbc.result;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "参数错误"),
    NO_AUTH(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    SERVER_ERROR(500, "服务器异常"),
    BUSINESS_ERROR(600, "业务异常");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}