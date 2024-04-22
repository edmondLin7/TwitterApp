package com.dbrowne.twitter.tweet.service.controller;


import com.dbrowne.twitter.tweet.service.entity.Tweet;
import com.dbrowne.twitter.tweet.service.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    // Get all tweets
    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        var data = tweetService.getAllTweets();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable("id") Long tweetId) {
        Tweet tweet = tweetService.getTweetById(tweetId);
        if (tweet == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    // Get tweets by user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Tweet>> getAllTweetsByUser(@PathVariable("username") String username) {
        var data = tweetService.getAllTweetsByUsername(username);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // post a tweet
    @PostMapping("/{username}/add")
    public ResponseEntity<Tweet> postTweet(
            @PathVariable("username") String username,
            @RequestBody Tweet tweet) {
        var data = tweetService.postTweet(username, tweet);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    // update a tweet
    @PutMapping("/{username}/update/{tweetId}")
    public ResponseEntity<Tweet> updateTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id,
            @RequestBody Tweet tweet) {
        var data = tweetService.updateTweet(username, id, tweet);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    // Delete a tweet
    @DeleteMapping("/{username}/delete/{tweetId}")
    public ResponseEntity<String> deleteTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id) {
        String result = tweetService.deleteTweet(username, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Like a tweet
    @PutMapping("/{username}/like/{tweetId}")
    public ResponseEntity<Tweet> likeTweet(
            @PathVariable("username") String username,
            @PathVariable("tweetId") Long id) {
        Tweet data = tweetService.likeTweet(username, id);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
