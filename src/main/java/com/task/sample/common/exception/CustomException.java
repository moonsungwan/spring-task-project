package com.task.sample.common.exception;

import com.task.sample.common.message.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final MessageCode messageCode;

}