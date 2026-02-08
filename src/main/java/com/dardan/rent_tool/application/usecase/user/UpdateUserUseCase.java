package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.command.UpdateUserCommand;
import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.UserDTOMapper;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.domain.validation.UserValidator;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {

    private final UserOutputPort userOutputPort;

    private final UserValidator validator = new UserValidator();
    private final UserDTOMapper mapper = new UserDTOMapper();

    public UpdateUserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public UserDTO execute(UpdateUserCommand command) {
        User user = userOutputPort.findById(command.getId())
            .orElseThrow(() -> new NotFoundException("userId", "El usuario no existe."));

        if (command.getFullName() != null) {
            validator.validateFullName(command.getFullName());
            user.setFullName(command.getFullName().trim());
        }

        if (command.getPhone() != null) {
            user.setPhone(command.getPhone());
        }

        if (command.getRole() != null) {
            validator.validateRole(command.getRole());
            user.setRole(command.getRole());
        }

        return mapper.toDTO(userOutputPort.save(user));
    }
}
