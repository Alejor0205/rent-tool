package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.UserDTOMapper;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserUseCase {

    private final UserOutputPort userOutputPort;
    private final UserDTOMapper mapper = new UserDTOMapper();

    public GetUserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public UserDTO execute(UUID id) {
        return userOutputPort.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("userId", "El usuario no existe."));
    }
}
