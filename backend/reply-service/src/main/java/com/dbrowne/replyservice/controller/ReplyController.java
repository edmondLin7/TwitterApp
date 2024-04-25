package com.dbrowne.replyservice.controller;

import com.dbrowne.replyservice.entity.Reply;
import com.dbrowne.replyservice.model.ReplyData;
import com.dbrowne.replyservice.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/replies")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    // Not in spec, but needed to display tweet thread with original tweet and replies
    @GetMapping("/{username}/{tweetId}")
    public ResponseEntity<List<ReplyData>> getAllReplies(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id) {
        var data = replyService.getAllRepliesByTweet(username, id);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PostMapping("/{username}/{tweetId}")
    public ResponseEntity<ReplyData> replyToTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id, @RequestBody Reply reply) {
        var data = replyService.replyToTweet(username, id, reply);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
/*
    @PostMapping("/{username}/replies/{tweetId}")
    public ResponseEntity<Reply> replyToTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id, @RequestBody Reply reply) {
        var data = replyService.replyToTweet(username, id, reply);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    */
    // Username is the person who liked it, not the person who owns the reply
    @PutMapping("/{username}/{replyId}/like")
    public ResponseEntity<Reply> likeTweet(
            @PathVariable("username") String username,
            @PathVariable("replyId") Long replyId
    ) {
        Reply data = replyService.likeReply(username, replyId);
        return ResponseEntity.ok(data);
    }
}