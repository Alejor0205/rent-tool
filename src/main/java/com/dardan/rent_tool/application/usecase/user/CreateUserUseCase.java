package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.command.CreateUserCommand;
import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.exception.ConflictException;
import com.dardan.rent_tool.application.mapper.UserDTOMapper;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.domain.validation.UserValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserOutputPort userOutputPort;
    private final UserValidator validator = new UserValidator();
    private final UserDTOMapper mapper = new UserDTOMapper();

    public CreateUserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public UserDTO execute(CreateUserCommand command) {
        validator.validateFullName(command.getFullName());
        validator.validateEmail(command.getEmail());
        validator.validateRole(command.getRole());

        if (userOutputPort.existsByEmailIgnoreCase(command.getEmail().trim())) {
            throw new ConflictException("user.email", "El email ya está registrado.");
        }

        User saved = userOutputPort.save(new User(
            null,
            command.getFullName().trim(),
            command.getEmail().trim(),
            command.getPhone(),
            command.getRole()
        ));
        return mapper.toDTO(saved);
    }
}
