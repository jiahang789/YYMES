package com.example.yymes.utils;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "成功"),
    /* 参数错误 */
    PARAM_IS_INVALID(101, "参数无效"),
    PARAM_IS_BLANK(102, "参数为空"),
    PARAM_TYPE_BIND_ERROR(103, "参数类型错误"),
    PARAM_NOT_COMPLETE(104, "参数缺失"),
    /* 用户错误 2001-2999*/
    USER_NOTLOGGED_IN(201, "用户未登录"),
    USER_LOGIN_ERROR(202, "账号不存在或密码错误"),
    SYSTEM_ERROR(100, "系统异常，请稍后重试");

    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }
    public String message() {
        return this.message;
    }
}