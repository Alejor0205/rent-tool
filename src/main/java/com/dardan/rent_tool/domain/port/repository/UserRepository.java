package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(UUID id);

    List<User> findAll();

    void deleteById(UUID id);

    boolean existsByEmailIgnoreCase(String email);
}
