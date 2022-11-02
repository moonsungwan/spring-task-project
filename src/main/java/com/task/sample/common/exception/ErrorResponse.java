package com.task.sample.common.exception;

import com.task.sample.common.message.MessageCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final int status;

    private final String error;

    private final String code;

    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(MessageCode messageCode) {
        return ResponseEntity
                .status(messageCode.getStatus())
                .body(ErrorResponse.builder()
                                   .status(messageCode.getStatus().value())
                                   .error(messageCode.getStatus().name())
                                   .code(messageCode.name())
                                   .message(messageCode.getMessage())
                                   .build()
                );
    }

}