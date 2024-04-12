package com.cogent.twitter.backend.controller;

import com.cogent.twitter.backend.entity.User;
import com.cogent.twitter.backend.payload.LoginDto;
import com.cogent.twitter.backend.payload.LoginResponse;
import com.cogent.twitter.backend.payload.RegisterDto;
import com.cogent.twitter.backend.payload.RegisterResponse;
import com.cogent.twitter.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@CrossOrigin
@RequestMapping("api/v1.0/tweets")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        LoginResponse response = authService.login(loginDto);
        if (response.isError()) {
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

    @GetMapping("users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = authService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("users/loginId/{loginId}")
    public ResponseEntity<User> getuserByName(@PathVariable("loginId") String loginId){
        User user = authService.getUserByLoginId(loginId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
