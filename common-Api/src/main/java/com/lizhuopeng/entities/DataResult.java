package com.lizhuopeng.entities;


import java.io.Serializable;

/**
 *
 * @author pandora gen
 * @param <T>
 */
public class DataResult<T> implements Serializable {
    private static final long serialVersionUID = -1954065564856833013L;

    private static final String DEFAULT_ERROR_MSG = "服务出小差，请稍后再试~";
    private static final String DEFAULT_TIMEOUT_MSG = "服务超时，请稍后再试~";

    /**
     *  返回码0 -> 成功,401 -> 没有权限,404 -> 查询的对象不存在,408 -> 请求超时,500 -> 未知错误  201~250 -> 业务自定义返回码
     */
    private int code = 0;
    /**
     * 提示语
     */
    private String message = "";
    /**
     * 返回数据
     */
    private T data;

    public DataResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataResult() {
    }

    public static <T> DataResult<T> success(T data, String message) {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.SUCCESS.getCode()).setMessage(message).setData(data);
        return result;
    }

    public static <T> DataResult<T> success(T data) {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.SUCCESS.getCode()).setData(data);
        return result;
    }

    public static <T> DataResult<T> timeout(String message) {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.TIMEOUT.getCode()).setMessage(message);
        return result;
    }

    public static <T> DataResult<T> timeout() {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.TIMEOUT.getCode()).setMessage(DEFAULT_TIMEOUT_MSG);
        return result;
    }

    public static DataResult<Void> of(Throwable throwable) {
        return of(throwable, null);
    }

    public static <T> DataResult<T> of(Throwable throwable, T data) {
        return DataResult.<T>serverError(throwable.getMessage()).setData(data);
    }

    public static <T> DataResult<T> of(RespCode respCode) {
        return of(respCode, null);
    }

    public static <T> DataResult<T> of(RespCode respCode, T data) {
        return new DataResult<T>().setData(data).setCode(respCode.getCode())
                .setMessage(respCode.getMessage());
    }

    public static <T> DataResult<T> serverError(String message) {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.UNKNOWN_ERROR.getCode()).setMessage(message);
        return result;
    }

    public static <T> DataResult<T> serverError() {
        DataResult<T> result = new DataResult<>();
        result.setCode(RespCode.UNKNOWN_ERROR.getCode()).setMessage(DEFAULT_ERROR_MSG);
        return result;
    }


    public int getCode() {
        return this.code;
    }

    public DataResult<T> setCode(int code) {
        this.code = code;
        return this;
    }


    public String getMessage() {
        return this.message;
    }

    public DataResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public DataResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
