package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.UserEntity;

public class UserMapper {
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
            entity.getId(),
            entity.getFullName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getRole()
        );
    }

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole()
        );
    }
}
