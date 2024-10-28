package com.java.util;

import com.java.constant.Constant;
import com.java.service.impl.LoggingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author hieu.nt60
 * 10/28/2024
 */
public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static void sendSMSTeams(String message, String urlWebHook) {
        // Tạo RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Tạo HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Chuỗi JSON với giá trị text từ biến
        String jsonBody = "{" +
                "\"type\": \"message\"," +
                "\"attachments\": [" +
                "{" +
                "\"contentType\": \"application/vnd.microsoft.card.adaptive\"," +
                "\"contentUrl\": null," +
                "\"content\": {" +
                "\"$schema\": \"http://adaptivecards.io/schemas/adaptive-card.json\"," +
                "\"type\": \"AdaptiveCard\"," +
                "\"version\": \"1.6\"," +
                "\"body\": [" +
                "{" +
                "\"type\": \"TextBlock\"," +
                "\"text\": \"" + DateTimeUtils.convertDateToString(new Date(), Constant.DateCode.DATE_FORMAT_DEFAULT) + "\"" +
                "}," +
                "{" +
                "\"type\": \"TextBlock\"," +
                "\"text\": \"" + message + "\"," +
                "\"fontType\": \"Monospace\"," +
                "\"horizontalAlignment\": \"Left\"," +
                "\"spacing\": \"ExtraLarge\"," +
                "\"wrap\": true" +
                "}" +
                "]" +
                "}" +
                "}" +
                "]" +
                "}";

        // Tạo HttpEntity chứa headers và body
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        // Gửi yêu cầu POST và nhận phản hồi
        ResponseEntity<String> response = restTemplate.postForEntity(urlWebHook, request, String.class);

        // Kiểm tra phản hồi
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Yêu cầu đã được gửi thành công!");
        } else {
            log.error("Lỗi trong quá trình gửi yêu cầu. Mã lỗi: " + response.getStatusCodeValue());
        }
    }
}
