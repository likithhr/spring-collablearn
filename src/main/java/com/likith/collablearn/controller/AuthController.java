package com.likith.collablearn.controller;

import com.likith.collablearn.dto.SignupRequest;
import com.likith.collablearn.dto.LoginRequest;
import com.likith.collablearn.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/signin") // This is important to match frontend!
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
