package com.dbrowne.twitter.tweet.service.service;

import com.dbrowne.twitter.tweet.service.entity.Tweet;

import java.util.List;

public interface TweetService {
    public List<Tweet> getAllTweets();
    public List<Tweet> getAllTweetsByUsername(String username);
    public List<Tweet> getAllTweetsByUserId(Long userId);
    public Tweet getTweetById(Long tweetId);
    public Tweet postTweet(String username, Tweet tweet);
    public Tweet updateTweet(String username, Long id, Tweet newTweet);
    public String deleteTweet(String username, Long id);
    public Tweet likeTweet(String username, Long id);
}
