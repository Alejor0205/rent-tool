package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import com.dardan.rent_tool.domain.model.enumm.RoleType;

public class CreateUserRequest {
    private String fullName;
    private String email;
    private String phone;
    private RoleType role;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String fullName, String email, String phone, RoleType role) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
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
