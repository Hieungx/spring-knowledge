package com.java.controller;

import com.java.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IJobService iJobService;

    @GetMapping
    public ResponseEntity<String> checkHealth(){
        iJobService.testJob();
        return ResponseEntity.ok(null);
    }

}
