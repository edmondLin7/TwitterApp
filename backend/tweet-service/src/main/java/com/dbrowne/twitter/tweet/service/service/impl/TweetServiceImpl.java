package com.dbrowne.twitter.tweet.service.service.impl;


import com.dbrowne.twitter.tweet.service.entity.Tweet;
import com.dbrowne.twitter.tweet.service.exception.CustomException;
import com.dbrowne.twitter.tweet.service.external.client.UserService;
import com.dbrowne.twitter.tweet.service.model.User;
import com.dbrowne.twitter.tweet.service.repository.TweetRepository;
import com.dbrowne.twitter.tweet.service.service.TweetService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private UserService userService;

    @Autowired TweetRepository tweetRepository;

    // How would this need to change at scale--finding all tweets would be too expensive to happen in app, replace with findNTweets?
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public List<Tweet> getAllTweetsByUsername(String username) {
        ResponseEntity<User> user = userService.getUserByUsername(username);
        if (user.getStatusCode() == HttpStatus.OK && user.getBody() != null) {
            return tweetRepository.findAllByUserId(user.getBody().getUserId());
        } else {
            throw new CustomException("Username not found", "USER_NOT_FOUND", 404);
        }
    }

    public List<Tweet> getAllTweetsByUserId(Long userId) {
        return tweetRepository.findAllByUserId(userId);
    }

    public Tweet postTweet(String username, Tweet tweet) {
        // Get user so we can associate it
        User user = getUserByUsername(username);
        // Set fields that are not passed directly in Tweet body
        tweet.setUserId(user.getUserId());
        tweet.setTimestamp(LocalDateTime.now());
        tweet.setLikeCount(0L);
        // save
        return tweetRepository.save(tweet);
    }

    public Tweet updateTweet(String username, Long id, Tweet newTweet) {
        // Get given user and original tweet
        User user = getUserByUsername(username);
        Tweet oldTweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        // ensure user is tweet owner
        if (!isTweetOwner(oldTweet.getUserId(), user)) {
            throw new RuntimeException("Cannot update a tweet that is not owned by you");
        }
        // update
        oldTweet.setTweetContent(newTweet.getTweetContent());
        oldTweet.setTag(newTweet.getTag());
        oldTweet.setTimestamp(LocalDateTime.now());
        // save
        return tweetRepository.save(oldTweet);
    }

    public String deleteTweet(String username, Long id) {
        // Get user and tweet
        User user = getUserByUsername(username);

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        // Ensure tweet is owned by user attempting to delete
        if (!isTweetOwner(tweet.getUserId(), user)) {
            throw new RuntimeException("Cannot delete a tweet that is not owned by you");
        }
        // Delete
        tweetRepository.delete(tweet);
        return "Tweet Deleted Successfully";
    }

    public Tweet likeTweet(String username, Long id) {
        User user = getUserByUsername(username);

        // Maybe we just do nothing in this case -- but it really shouldn't happen how do you delete a tweet that doesnt exist
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));

        tweet.setLikeCount(tweet.getLikeCount() + 1);
        return tweetRepository.save(tweet);
    }

    private User getUserByUsername(String username) {
        User user = null;
        try {
            user = userService.getUserByUsername(username).getBody();
            log.info("User retrieved from userService with username: {}", username);
        } catch (Exception ex) {
            log.error("User could not be retrieved from userService");
        }
        if (user == null) {
            throw new CustomException("User not found", "UserServiceError", 404);
        }
        return user;
    }

    private boolean isTweetOwner(Long tweetUserId, User user) {
        return (Objects.equals(tweetUserId, user.getUserId()));
    }

}
