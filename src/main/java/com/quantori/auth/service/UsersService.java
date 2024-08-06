package com.quantori.auth.service;

import com.quantori.auth.model.dto.UsersResponse;

public interface UsersService {
    UsersResponse getProfile(String bearerToken);
}
