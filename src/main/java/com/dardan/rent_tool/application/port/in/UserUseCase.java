package com.dardan.rent_tool.application.port.in;

import com.dardan.rent_tool.application.command.CreateUserCommand;
import com.dardan.rent_tool.application.command.UpdateUserCommand;
import com.dardan.rent_tool.application.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {
    UserDTO create(CreateUserCommand command);

    UserDTO update(UpdateUserCommand command);

    UserDTO get(UUID id);

    List<UserDTO> list();

    void delete(UUID id);
}
