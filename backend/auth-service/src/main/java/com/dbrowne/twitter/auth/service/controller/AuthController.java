package com.dbrowne.twitter.auth.service.controller;


import com.dbrowne.twitter.auth.service.entity.User;
import com.dbrowne.twitter.auth.service.payload.*;
import com.dbrowne.twitter.auth.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1.0/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {

        LoginResponse response = authService.login(loginDto);
        if (response.isError()) {
            response.setMessage("Username or password is incorrect");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        RegisterResponse response = authService.register(registerDto);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }

    @PutMapping("/reset-password/{username}")
    public ResponseEntity<PasswordResetResponse> resetPassword(
            @PathVariable("username") String username,
            @RequestBody String password) {
        PasswordResetResponse response = authService.resetPassword(username, password);
        if (response.isError()) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = authService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = authService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
