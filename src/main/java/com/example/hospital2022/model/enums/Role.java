package com.example.hospital2022.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}