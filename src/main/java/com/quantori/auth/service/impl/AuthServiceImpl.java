package com.quantori.auth.service.impl;

import com.quantori.auth.exception.CustomNotFoundException;
import com.quantori.auth.exception.CustomPasswordNotCorrectException;
import com.quantori.auth.model.dto.AccessTokenResponse;
import com.quantori.auth.model.dto.LoginRequest;
import com.quantori.auth.model.entity.UsersEntity;
import com.quantori.auth.repository.UsersRepository;
import com.quantori.auth.service.AuthService;
import com.quantori.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AccessTokenResponse login(LoginRequest loginRequest) {
        UsersEntity usersEntity = findByEmail(loginRequest);
        boolean isPassMatched = comparePassword(loginRequest, usersEntity);
        if (!isPassMatched){
            throw new CustomPasswordNotCorrectException("Password not correct!");
        }
        String accessToken = jwtService.generateToken(usersEntity);

        return new AccessTokenResponse(accessToken);
    }

    private boolean comparePassword(LoginRequest loginRequest, UsersEntity usersEntity) {
        return passwordEncoder.matches(loginRequest.getPassword(), usersEntity.getPassword());
    }

    private UsersEntity findByEmail(LoginRequest loginRequest) {
        return usersRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new CustomNotFoundException("User not found by email " + loginRequest.getEmail()));
    }
}
