package com.cogent.twitter.backend.service;

import com.cogent.twitter.backend.entity.User;
import com.cogent.twitter.backend.payload.*;
import com.cogent.twitter.backend.repository.UserRepository;
import com.cogent.twitter.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public LoginResponse login(LoginDto loginDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()
            ));
        } catch (AuthenticationException exception) {
            LoginResponse errResponse = new LoginResponse();
            errResponse.setError(true);
            errResponse.setMessage("login failed, username or password is incorrect");
            return errResponse;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("login successful");
        response.setError(false);

        return response;
    }

    public RegisterResponse register(RegisterDto registerDto) {
        // add check for username exists in database
        if (userRepository.existsByLoginId(registerDto.getLoginId())) {
            RegisterResponse response = new RegisterResponse(
                    true, "Registration failed: Username already exists", null);
            return response;
        }
        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            RegisterResponse response = new RegisterResponse(
                    true, "Registration failed: Email already exists", null);
            return response;
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setLoginId(registerDto.getLoginId());
        user.setEmail(registerDto.getEmail());
        user.setContactNumber(registerDto.getContactNumber());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        User savedUser =  userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setError(false);
        response.setMessage("Registration successful");
        response.setUser(savedUser);
        return response;
    }

    public PasswordResetResponse resetPassword(String loginId, String password) {
        User user;
        if (!userRepository.existsByLoginId(loginId)) {
            PasswordResetResponse response = new PasswordResetResponse(
                    "Cannot reset password: Username does not exist", true);
            return response;
        }
        user = userRepository.findUserByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("This can't happen"));
        // add check for email exists in database
        if (!userRepository.existsByEmail(user.getEmail())) {
            PasswordResetResponse response = new PasswordResetResponse(
                    "Cannot reset password: Email does not exist", true);
            return response;
        }
        User curUser = getUserById(user.getUserId());
        curUser.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepository.save(curUser);
        PasswordResetResponse response = new PasswordResetResponse(
                "Password reset successful", false);
        return response;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByLoginId(String loginId) {
        return userRepository.findUserByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
