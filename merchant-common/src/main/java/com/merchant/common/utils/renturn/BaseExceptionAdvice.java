package com.merchant.common.utils.renturn;

import org.springframework.http.HttpStatus;

public class BaseExceptionAdvice {

    /**
     * 失败
     *
     * @return {Object}
     */
    public Object renderError(HttpStatus status) {
        Result result = new Result();
        result.setSuccess(false);
        result.setStatus(status.value());
        return result;
    }

    /**
     * 失败
     *
     * @return {Object}
     */
    public Object renderError(Integer status, String msg, String path) {
        Result result = new Result();
        result.setSuccess(false);
        result.setStatus(status);
        result.setData(msg);
        return result;
    }

    /**
     * 失败
     *
     * @return {Object}
     */
    public Object renderError(HttpStatus status, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setStatus(status.value());
        result.setMsg(msg);
        return result;
    }
}
