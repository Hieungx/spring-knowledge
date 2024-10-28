package com.java.util;

import com.java.config.exception.CustomBusinessException;
import com.java.service.impl.JobServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hieu.nt60
 * 10/28/2024
 */
public class DateTimeUtils {
    private static final Logger log = LoggerFactory.getLogger(DateTimeUtils.class);

    public static String convertDateToString(Date date, String formatType) {
        if (!ObjectUtils.isEmpty(date)) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(formatType);
                return dateFormat.format(date);
            } catch (Exception e) {
                log.error("convertDateToString() INVALID: ", date);
            }
        }
        throw new CustomBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "");
    }
}
