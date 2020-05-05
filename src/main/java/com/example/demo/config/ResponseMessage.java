package com.example.demo.config;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 统一返回类型
 * @param <T>
 */
@Data
public class ResponseMessage<T> {
    private int code;
    private String msg;
    public T data;

    public ResponseMessage() {
    }

    public ResponseMessage(int code) {
        this.code = code;
    }

    public ResponseMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMessage(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseMessage success() {
        return new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static ResponseMessage success(Object data) {
        return new ResponseMessage(HttpStatus.OK.value(),  HttpStatus.OK.getReasonPhrase(), data);
    }

    public static ResponseMessage fail() {
        return new ResponseMessage(HttpStatus.BAD_REQUEST.value(),  HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

}
