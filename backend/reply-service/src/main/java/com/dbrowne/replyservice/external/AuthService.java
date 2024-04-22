package com.dbrowne.replyservice.external;

import com.dbrowne.replyservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="AUTH-SERVICE/api/v1.0/auth")
public interface AuthService {
    @GetMapping("users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
