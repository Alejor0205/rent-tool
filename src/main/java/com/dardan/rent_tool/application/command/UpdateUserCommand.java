package com.dardan.rent_tool.application.command;

import com.dardan.rent_tool.domain.model.enumm.RoleType;

import java.util.UUID;

public class UpdateUserCommand {
    private UUID id;
    private String fullName;
    private String phone;
    private RoleType role;

    public UpdateUserCommand() {
    }

    public UpdateUserCommand(UUID id, String fullName, String phone, RoleType role) {
        this.id = id;
        this.fullName = fullName;
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
