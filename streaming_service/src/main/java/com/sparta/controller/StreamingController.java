package com.sparta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamingController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
