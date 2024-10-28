package com.java.config.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        response = fullLog(request, body, response);
        return response;
    }

    // Tránh việc trong log xuất hiện base64
    private ClientHttpResponse logWithOutRequestBody(HttpRequest request, ClientHttpResponse response) throws IOException {
        final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
        log.info("Method: " + request.getMethod());
        log.info("URI: " + request.getURI());
        log.info("Status Code: " + responseCopy.getStatusCode());
        return responseCopy;
    }

    private ClientHttpResponse logWithOutResponseBody(HttpRequest request, final byte[] body, ClientHttpResponse response) throws IOException {
        final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
        log.info("Method: " + request.getMethod());
        log.info("URI: " + request.getURI());
        log.info("Request Body: " + new String(body));
        log.info("Status Code: " + responseCopy.getStatusCode());
        return responseCopy;
    }

    private ClientHttpResponse fullLog(final HttpRequest request, final byte[] body, final ClientHttpResponse response) throws IOException {
        final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
        log.info("Method: " + request.getMethod());
        log.info("URI: " + request.getURI());
        log.info("Request Body: " + new String(body));
        log.info("Response Body: " + StreamUtils.copyToString(responseCopy.getBody(), StandardCharsets.UTF_8));
        log.info("Status Code: " + responseCopy.getStatusCode());
        return responseCopy;
    }
}
