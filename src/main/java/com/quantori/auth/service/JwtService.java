package com.quantori.auth.service;

import com.quantori.auth.model.entity.UsersEntity;

public interface JwtService {

    String extractUsername(String token);

    String generateToken(UsersEntity usersEntity);

    void isTokenValid(String token, UsersEntity authUsersEntity);
}
