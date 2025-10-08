package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegister {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email ;

    @NotBlank
    private String password;

    private Set<String> roles = new HashSet<>();

}
