package com.auth.util;

public enum ResultCodeEnum {
    SUCCESS(true,20000,"成功"),
    UNKNOWN_ERROR(false,20001,"未知错误"),
    PARAM_ERROE(false,20002,"参数错误"),
    NULL_POINT(false,20003,"空指针异常"),
    LOGIN_SUCCESS(true,20004,"登录成功"),
    NOT_LOGIN(false,20005,"您还没有登录，请登录过后再次访问"),
    LOGOUT_SUCCESS(false,20006,"登出成功"),
    NO_AUTHORITY(false,20007,"没有权限访问"),
    TOKEN_EXPIRED(false,20008,"TOKEN过期"),
    RESULT_NONE(false,20009,"结果不存在"),
    INVALID_TOKEN(false,20010,"TOKEN无效");


    //响应是否成功
    private Boolean success;
    //响应状态码
    private Integer code;
    //响应信息
    private String message;

    ResultCodeEnum(boolean success,Integer code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
