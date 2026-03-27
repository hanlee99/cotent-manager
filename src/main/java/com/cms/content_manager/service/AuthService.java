package com.cms.content_manager.service;

import com.cms.content_manager.entity.SiteUser;
import com.cms.content_manager.entity.UserRole;
import com.cms.content_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(String username, String password, UserRole role) {
        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 2. 유저 생성 및 저장
        SiteUser user = SiteUser.builder()
                .username(username)
                .password(encodedPassword)
                .role(role)
                .build();

        userRepository.save(user);
    }
}
