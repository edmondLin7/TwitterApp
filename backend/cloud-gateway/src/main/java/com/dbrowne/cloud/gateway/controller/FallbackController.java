package com.dbrowne.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @PostMapping("authServiceFallback")
    public String authServiceFallback() {
        return "Authentication service is down";
    }

    @GetMapping("tweetServiceFallback")
    public String tweetServiceFallback() {
        return "Tweet service is down";
    }

    @GetMapping("replyServiceFallback")
    public String replyServiceFallback() {
        return "Reply service is down";
    }


}
