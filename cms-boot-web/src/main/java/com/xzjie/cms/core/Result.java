package com.xzjie.cms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xzjie.cms.core.utils.ResultCode;
import com.xzjie.cms.enums.Business;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@ApiModel("返回结果")
public final class Result<T extends Object> {

    @ApiModelProperty("code")
    private int code;
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("信息内容")
    private String message;
    @ApiModelProperty(value = "错误信息",hidden = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonIgnore
    @ApiModelProperty("时间")
    private Instant time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("对象类型")
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("总数量")
    private long total;


    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }


    public Result(boolean success, int code, String message, T data) {
        this();
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        return success(Business.SUCCESS.getCode(), Business.SUCCESS.getMessage());
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    public static <T> Result<T> success(String message) {
        return success(Business.SUCCESS.getCode(), message);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    @Deprecated
    public static <T> Result<T> success(T data) {
        return (Result<T>) success().setData(data);
    }

    public static <T> Result<T> data(T data) {
        return (Result<T>) success().setData(data);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    @Deprecated
    public static <T> Result<T> success(T data, long total) {
        return success(data).setTotal(total);
    }

    public static <T> Result<T> data(T data, long total) {
        return success(data).setTotal(total);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    public static <T> Result<T> success(int code, String message) {
        return success(code, message, null);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    @Deprecated
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(true, code, message, data);
    }

    public static <T> Result<T> data(int code, String message, T data) {
        return new Result<>(true, code, message, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param code
     * @return Result
     */
    public Result fail(int code, String message) {
        return setCode(code)
                .setMessage(message)
//                .setError(error)
                .setSuccess(false);
    }

    public Result fail(String message) {
        return setCode(Business.ERROR.getCode())
                .setMessage(message)
//                .setError(error)
                .setSuccess(false);
    }

    public Result fail(Business business) {
        return setCode(business.getCode())
                .setMessage(business.getMessage())
//                .setError(error)
                .setSuccess(false);
    }


    private Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    private Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    private Result<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    private Result<T> setError(String error) {
        this.error = error;
        return this;
    }

    private Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    private Result<T> setTotal(long total) {
        this.total = total;
        return this;
    }
}
