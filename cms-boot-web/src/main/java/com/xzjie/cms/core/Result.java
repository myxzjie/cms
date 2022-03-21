package com.xzjie.cms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xzjie.cms.enums.Business;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
public final class Result<T extends Object> {

    private int code;
    private boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonIgnore
    private Instant time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
    public static <T> Result<T> success(T data) {
        return (Result<T>) success().setData(data);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @return Result
     */
    public static <T> Result<T> success(T data, long total) {
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
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(true, code, message, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param code
     * @return Result
     */
    public Result fail(int code, String message, String error) {
        return setCode(code).setMessage(message).setError(error).setSuccess(false);
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
