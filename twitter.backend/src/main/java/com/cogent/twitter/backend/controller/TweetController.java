package com.cogent.twitter.backend.controller;

import com.cogent.twitter.backend.entity.Tweet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {
    // Get all tweets
    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        return null;
    }

    // Get tweets by user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Tweet>> getAllTweetsByUser(@PathVariable("username") String username) {
        return null;
    }

    // post a tweet
    @PostMapping("/{username}/add")
    public ResponseEntity<Tweet> postTweet(
            @PathVariable("username") String username,
            @RequestBody Tweet tweet) {
        return null;
    }

    // update a tweet
    @PutMapping("/{username}/update/{tweetId}")
    public ResponseEntity<Tweet> updateTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id,
            @RequestBody Tweet tweet) {
        return null;
    }

    // Delete a tweet
    @DeleteMapping("/{username}/delete/{tweetId}")
    public ResponseEntity<Tweet> deleteTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id) {
        return null;
    }

    // Like a tweet
    @PutMapping("/{username}/like/{tweetId}")
    public ResponseEntity<Tweet> likeTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id,
            @RequestBody Tweet tweet) {
        return null;
    }

}
