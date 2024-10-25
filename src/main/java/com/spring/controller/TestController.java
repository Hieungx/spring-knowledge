package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hieu.nt60
 * 10/25/2024
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public ResponseEntity<String> checkHealth(){
        log.info("My message set at info level");
        return ResponseEntity.ok(null);
    }

}
