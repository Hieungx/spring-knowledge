package com.java.config;

import com.java.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hieu.nt60
 * 10/29/2024
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.java.service..*(..))")
    public void applicationPackagePointcut() {
        // Pointcut để bắt các method trong package com.example.yourpackage
    }


    // Sử dụng Around để log request và response
    @Around("applicationPackagePointcut()")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        // Log thông tin request truyền vào
        Object[] args = joinPoint.getArgs();
        logger.info("Request to {} with args: {}", joinPoint.getSignature(), args);

        // Thực hiện method và lấy response
        Object result;
        String jsonResponse;
        try {
            result = joinPoint.proceed();
            jsonResponse = ObjectUtils.isEmpty(result) ? null : JsonUtils.convertObjectToJson(result);
        } catch (Exception e) {
            logger.error("Exception in method {}: {}", joinPoint.getSignature(), ExceptionUtils.getStackTrace(e));
            throw e;
        }
        // Log thông tin response trả về nếu có
        logger.info("Response from {}: {} - Time taken: {} ms", joinPoint.getSignature(), jsonResponse, System.currentTimeMillis() - startTime);
        return result;
    }
}
