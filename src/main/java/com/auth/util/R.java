package com.auth.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果返回类
 *
 * @author xhl
 * @create 2020/7/19
 */
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<>();

    //构造器私有
    private R(){}

    //通用返回成功
    public static R ok(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    //通用返回失败，未知错误
    public static R error(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    //通用返回失败，参数错误
    public static R param(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.PARAM_ERROE.getSuccess());
        r.setCode(ResultCodeEnum.PARAM_ERROE.getCode());
        r.setMessage(ResultCodeEnum.PARAM_ERROE.getMessage());
        return r;
    }

    //没有结果返回
    public static R noneResult(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.RESULT_NONE.getSuccess());
        r.setCode(ResultCodeEnum.RESULT_NONE.getCode());
        r.setMessage(ResultCodeEnum.RESULT_NONE.getMessage());
        return r;
    }

    //设置结果，形参为结果枚举
    public static R setResult(ResultCodeEnum result){
        R r = new R();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /** 链式编程，返回类本身**/

    //自定义返回数据
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    //通用设置data
    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    //自定义状态信息
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    //自定义状态码
    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    //自定义返回结果
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
