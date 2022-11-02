package com.task.sample.common.entity;

import com.task.sample.common.message.MessageCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseEntity<T> {

    private boolean success = true;

    private T data = null;

    private String message = MessageCode.SUCCEED.getMessage();

    public ApiResponseEntity(T data) {
        this.data = data;
    }

    public ApiResponseEntity(T data, MessageCode message) {
        this.data = data;
        this.message = message.getMessage();
    }

    public ApiResponseEntity(MessageCode message) {
        this.message = message.getMessage();
    }

    public ApiResponseEntity(T data, int result) {
        this.data = data;
        this.message = result > 0 ? MessageCode.SUCCEED.getMessage() : MessageCode.FAILED.getMessage();
    }

    public ApiResponseEntity(boolean success, T data, MessageCode message) {
        this.success = success;
        this.data = data;
        this.message = message.getMessage();
    }

}
