package com.cogent.twitter.backend.controller;


import com.cogent.twitter.backend.entity.Reply;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class ReplyController {

    // Not in spec, but needed to display tweet thread with original tweet and replies
    @GetMapping("/replies/{tweetId}")
    public ResponseEntity<List<Reply>> getAllReplies(
            @PathVariable("tweetId") Long id) {
        return null;
    }

    @PostMapping("/{username}/reply/{tweetId}")
    public ResponseEntity<Reply> replyToTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id) {
        return null;
    }
}
