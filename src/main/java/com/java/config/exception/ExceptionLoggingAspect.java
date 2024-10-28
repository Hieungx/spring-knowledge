package com.java.config.exception;

import com.java.constant.Constant;
import com.java.util.DateTimeUtils;
import com.java.util.Utils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hieu.nt60
 * 10/28/2024
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionLoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @Value("${incoming-webhook-channel}")
    private String incomingWebhookUrl;

    // Phương thức này sẽ được gọi khi có ngoại lệ xảy ra
    @AfterThrowing(pointcut = "execution(* com.java..*(..))", throwing = "ex")
    public void logException(Exception ex) {
        // Tạo thông báo lỗi
        String errorMessage = "SYSDATE: " + DateTimeUtils.convertDateToString(new Date(), Constant.DateCode.DATE_FORMAT_DEFAULT) +
                "- SERVICE SPRING-KNOWLEDGE HAS EXCEPTION: " + ex.getMessage();

        // Gửi thông báo qua webhook
        Utils.sendSMSTeams(errorMessage, incomingWebhookUrl);
    }
}
