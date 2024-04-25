package com.dbrowne.twitter.tweet.service.service;

import com.dbrowne.twitter.tweet.service.entity.Tweet;
import com.dbrowne.twitter.tweet.service.model.TweetResponse;

import java.util.List;

public interface TweetService {
    public List<TweetResponse> getAllTweets();
    public List<TweetResponse> getAllTweetsByUsername(String username);
    public List<TweetResponse> getAllTweetsByUserId(Long userId);
    public TweetResponse getTweetById(Long tweetId);
    public TweetResponse postTweet(String username, Tweet tweet);
    public TweetResponse updateTweet(String username, Long id, Tweet newTweet);
    public String deleteTweet(String username, Long id);
    public TweetResponse likeTweet(String username, Long id);
}
