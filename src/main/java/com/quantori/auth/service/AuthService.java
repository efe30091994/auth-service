package com.quantori.auth.service;

import com.quantori.auth.model.dto.AccessTokenResponse;
import com.quantori.auth.model.dto.LoginRequest;

public interface AuthService {
    AccessTokenResponse login(LoginRequest loginRequest);
}
