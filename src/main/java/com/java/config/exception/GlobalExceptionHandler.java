package com.java.config.exception;

import com.java.controller.TestController;
import com.java.dto.response.MultiMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hieu.nt60
 * 10/28/2024
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public MultiMessageResponse<?> handleRuntimeException(RuntimeException ex, HttpServletResponse response) {
        log.error(ExceptionUtils.getStackTrace(ex));
        response.setStatus(HttpStatus.OK.value());
        return new MultiMessageResponse<>(null, "Service is having problems", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
