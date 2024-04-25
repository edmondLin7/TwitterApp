package com.dbrowne.twitter.tweet.service.external.client;

import com.dbrowne.twitter.tweet.service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="AUTH-SERVICE/api/v1.0/auth")
public interface UserService {
    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);

    @GetMapping("/users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username);
}
