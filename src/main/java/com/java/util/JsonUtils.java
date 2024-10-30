package com.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author hieu.nt60
 * 10/30/2024
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Optional: Enable pretty printing for JSON output
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static String convertObjectToJson(Object obj) {
        try {
            // Convert the object to a JSON string
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
