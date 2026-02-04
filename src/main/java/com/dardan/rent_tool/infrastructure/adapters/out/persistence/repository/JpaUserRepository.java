package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmailIgnoreCase(String email);
}
