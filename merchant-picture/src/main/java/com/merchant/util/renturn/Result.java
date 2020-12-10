package com.merchant.util.renturn;

import lombok.Data;

@Data
public class Result {
    private boolean success;
    private String msg;
    private Integer status;
    private Object data;

    public Result() {
    }

    public Result(Object data) {
        this.success = true;
        status = 200;
        this.data = data;
    }

    public Result(String msg, Object data) {
        this.success = true;
        status = 200;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 获取成功返回值
     *
     * @param msg  信息
     * @param data 数据
     */
    public static Result success(String msg, Object data) {
        return new Result(msg, data);
    }

    /**
     * 获取成功返回值
     *
     * @param data 数据
     */
    public static Result success(Object data) {
        return new Result(data);
    }

    /**
     * 获取成功返回值
     *
     * @param msg 信息
     */
    public static Result success(String msg) {
        return new Result(msg, null);
    }

    /**
     * 获取成功返回值
     */
    public static Result success() {
        return new Result(null, null);
    }
}
