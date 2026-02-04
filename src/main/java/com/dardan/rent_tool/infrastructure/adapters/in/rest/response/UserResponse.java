package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import com.dardan.rent_tool.domain.model.enumm.RoleType;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private RoleType role;

    public UserResponse() {
    }

    public UserResponse(UUID id, String fullName, String email, String phone, RoleType role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
