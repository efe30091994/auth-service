package com.quantori.auth.controller;

import com.quantori.auth.model.dto.UsersResponse;
import com.quantori.auth.model.entity.UsersEntity;
import com.quantori.auth.repository.UsersRepository;
import com.quantori.auth.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/profile")
    public UsersResponse getProfile(@RequestHeader("Authorization") String bearerToken) {
        return usersService.getProfile(bearerToken);
    }
}
