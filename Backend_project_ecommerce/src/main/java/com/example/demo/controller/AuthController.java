package com.example.demo.controller;

import com.example.demo.dto.RequestLogin;
import com.example.demo.dto.RequestRegister;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser( @Valid @RequestBody RequestRegister requestRegister){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(requestRegister));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> loginUser(@Valid @RequestBody RequestLogin requestLogin){
        return ResponseEntity.ok(authService.login(requestLogin));

    }


}
