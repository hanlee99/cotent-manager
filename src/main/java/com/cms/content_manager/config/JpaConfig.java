package com.cms.content_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        /**
         * 생성자(created_by)를 누구로 할지 결정하는 로직입니다.
         * 아직 로그인을 붙이기 전이라면 에러 방지를 위해 "SYSTEM"을 기본값으로 줍니다.
         * 나중에 시큐리티를 붙이면 로그인한 유저 ID가 자동으로 들어갑니다.
         */
        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName) // 로그인한 사람 이름 가져오기
                .or(() -> Optional.of("SYSTEM")); // 로그인 전이라면 "SYSTEM"으로 기록
    }
}
