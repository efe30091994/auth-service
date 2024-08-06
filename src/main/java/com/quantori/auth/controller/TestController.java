package com.quantori.auth.controller;

import com.quantori.auth.model.entity.UsersEntity;
import com.quantori.auth.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller only for creating test user
 */

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public UsersEntity test() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername("era");
        usersEntity.setEmail("efe30091994@mail.ru");
        usersEntity.setFullName("Yerassyl Seiitkhanov");
        String encoded = passwordEncoder.encode("Quantori2024!");
        usersEntity.setPassword(encoded);

        return usersRepository.save(usersEntity);
    }
}
