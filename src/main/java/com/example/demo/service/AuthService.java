package com.example.demo.service;

import com.example.demo.dto.RequestLogin;
import com.example.demo.dto.RequestRegister;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.dto.UserDTO;
import jakarta.validation.Valid;

public interface AuthService {
    UserDTO register(RequestRegister requestRegister);

    ResponseLogin login(@Valid RequestLogin requestLogin);
}
