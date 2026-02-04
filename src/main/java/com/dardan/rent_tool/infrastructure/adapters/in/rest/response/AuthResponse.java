package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import com.dardan.rent_tool.domain.model.enumm.RoleType;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;
    private RoleType role;

    public AuthResponse() {
    }

    public AuthResponse(String token, UUID userId, RoleType role) {
        this.token = token;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
