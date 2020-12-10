package com.merchant.util.renturn;

import org.apache.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionAdvice extends BaseExceptionAdvice {


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMissingServletRequestParameterException() {
        return renderError(HttpStatus.BAD_REQUEST, "请求错误");
    }

    /**
     * 404 - Bad Request
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpException.class)
    public Object notFoundPage() {
        return renderError(HttpStatus.BAD_REQUEST, "该资源不存在");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException() {
        return renderError(HttpStatus.METHOD_NOT_ALLOWED, "请求方法拒绝访问");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException() {
        return renderError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "请求数据格式错误");
    }

    /**
     * 500 - Internal Server Error
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    public Object handleException(Exception e) {
//        return renderError(HttpStatus.INTERNAL_SERVER_ERROR, "系统异常");
//    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MyException.class)
    public Object handleServiceException(MyException e) {
        return renderError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
