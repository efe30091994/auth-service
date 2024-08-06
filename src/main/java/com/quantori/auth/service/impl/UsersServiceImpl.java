package com.quantori.auth.service.impl;

import com.quantori.auth.exception.CustomNotFoundException;
import com.quantori.auth.mapper.UsersMapper;
import com.quantori.auth.model.dto.UsersResponse;
import com.quantori.auth.model.entity.UsersEntity;
import com.quantori.auth.repository.UsersRepository;
import com.quantori.auth.service.JwtService;
import com.quantori.auth.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    @Override
    public UsersResponse getProfile(String bearerToken) {
        String token = getToken(bearerToken);
        String username = extracted(token);
        UsersEntity usersEntity = findByUsername(username);
        return UsersMapper.INSTANCE.toUsersResponseDto(usersEntity);
    }

    private String extracted(String token) {
        return jwtService.extractUsername(token);
    }

    private UsersEntity findByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("Profile not found!"));
    }

    private String getToken(String token) {
        return token.substring(7);
    }
}
