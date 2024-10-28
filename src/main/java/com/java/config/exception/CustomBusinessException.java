package com.java.config.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Data
@ResponseStatus(HttpStatus.OK)
public class CustomBusinessException extends RuntimeException {
    String code;
    List<String> messages;
    HttpHeaders httpHeaders;

    public CustomBusinessException(HttpStatus code, List<String> message) {
        this.code = String.valueOf(code);
        this.messages = message;
    }

    public CustomBusinessException(HttpStatus code, String message) {
        this.code = String.valueOf(code);
        this.messages = Collections.singletonList(message);
    }

    public CustomBusinessException(String message) {
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.messages = Collections.singletonList(message);
    }

    public CustomBusinessException(HttpStatus code, List<String> message, HttpHeaders httpHeaders) {
        this.code = String.valueOf(code);
        this.messages = message;
        this.httpHeaders = httpHeaders;
    }

}
