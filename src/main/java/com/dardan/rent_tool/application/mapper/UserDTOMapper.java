package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.domain.model.entity.User;

public class UserDTOMapper {
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole()
        );
    }
}
