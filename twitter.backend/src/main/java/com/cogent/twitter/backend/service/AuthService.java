package com.cogent.twitter.backend.service;

import com.cogent.twitter.backend.entity.User;
import com.cogent.twitter.backend.payload.LoginDto;
import com.cogent.twitter.backend.payload.RegisterDto;
import com.cogent.twitter.backend.payload.LoginResponse;
import com.cogent.twitter.backend.payload.RegisterResponse;
import com.cogent.twitter.backend.repository.UserRepository;
import com.cogent.twitter.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

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
                    true, "Username exists", null);
            return response;
        }
        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            RegisterResponse response = new RegisterResponse(
                    true, "Email already exists", null);
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
        response.setMessage("register successful");
        response.setUser(savedUser);
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
