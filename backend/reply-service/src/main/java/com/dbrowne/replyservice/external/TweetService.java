package com.dbrowne.replyservice.external;
import com.dbrowne.replyservice.model.Tweet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="TWEET-SERVICE/api/v1.0/auth")
public interface TweetService {
    @GetMapping("/users/id/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable("id") Long tweetId);
/*
    @GetMapping("/users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username);

 */
}
