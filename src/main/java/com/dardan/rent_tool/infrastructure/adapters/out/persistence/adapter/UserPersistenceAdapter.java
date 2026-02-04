package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.UserMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserOutputPort {

    private final JpaUserRepository userRepository;
    private final UserMapper mapper = new UserMapper();

    public UserPersistenceAdapter(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(userRepository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }
}
