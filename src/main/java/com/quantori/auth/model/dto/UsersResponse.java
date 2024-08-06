package com.quantori.auth.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersResponse {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
