package com.quantori.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email should not be empty!")
    private String email;
    @NotBlank(message = "password should not be empty!")
    private String password;
}
