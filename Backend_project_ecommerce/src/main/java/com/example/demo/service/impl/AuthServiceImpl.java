package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.util.JwtUtil;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder ;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserDTO register(RequestRegister requestRegister) {
       if (requestRegister.getUsername()== null || requestRegister.getUsername().isBlank()){
           throw new IllegalArgumentException("username cannot be blank");
       }

       if (userRepository.findByUsername(requestRegister.getUsername()).isPresent()){
           throw new IllegalArgumentException("username already exists");
       }

       if (userRepository.findByEmail(requestRegister.getEmail()).isPresent()){
           throw new IllegalArgumentException("Email already in use ");
       }

        User user = new User();
       user.setUsername(requestRegister.getUsername());
       user.setPassword(passwordEncoder.encode(requestRegister.getPassword()));
       user.setEmail(requestRegister.getEmail());

        Set<Role> roles = requestRegister.getRoles()
                        .stream()
                                .map(roleRepository::findByName)
                                        .collect(Collectors.toSet());


       user.setRoles(roles);

       return userMapper.mapToDto(userRepository.save(user));
    }

    @Override
    public ResponseLogin login(RequestLogin requestLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getUsername(),
                requestLogin.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

        String jwtToken = jwtUtil.generateSecretKey(userPrincipal);

        return new ResponseLogin(userPrincipal.getId(), jwtToken);


    }

}
