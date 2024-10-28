package com.java.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * @author hieu.nt60
 * 12/21/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultiMessageResponse<T> {
    private T data;
    private List<String> message;
    private String code;

    public MultiMessageResponse(T data, String message, String code) {
        this.data = data;
        this.message = Collections.singletonList(message);
        this.code = code;
    }

    public static MultiMessageResponse<Object> ok(Object data) {
        return MultiMessageResponse.builder()
                .data(data)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(Collections.singletonList("Successful")).build();
    }

    public static MultiMessageResponse<Object> createSuccess(Object data) {
        return MultiMessageResponse.builder()
                .data(data)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message(Collections.singletonList("Create success")).build();
    }

    public static MultiMessageResponse<Object> updateSuccess(Object data) {
        return MultiMessageResponse.builder()
                .data(data)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(Collections.singletonList("Update success")).build();
    }

    public static MultiMessageResponse<Object> deleteSuccess() {
        return MultiMessageResponse.builder()
                .data(null)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(Collections.singletonList("Delete success")).build();
    }
}
