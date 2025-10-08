package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {

    @NotBlank(message = "username cannot be blank ")
    private String username;

    @NotBlank(message = "password cannot be blank ")
    private String password;


}
