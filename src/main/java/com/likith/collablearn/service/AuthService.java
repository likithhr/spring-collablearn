package com.likith.collablearn.service;

import com.likith.collablearn.dto.SignupRequest;
import com.likith.collablearn.dto.LoginRequest;
import com.likith.collablearn.entity.User;
import com.likith.collablearn.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Error: Email already in use!";
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hashing password
        user.setRole(request.getRole());

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return "Login successful!"; // Later replace with JWT token
            }
        }
        return "Invalid email or password!";
    }
}
