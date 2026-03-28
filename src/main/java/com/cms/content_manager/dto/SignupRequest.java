package com.cms.content_manager.dto;

import com.cms.content_manager.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private UserRole role;
}
