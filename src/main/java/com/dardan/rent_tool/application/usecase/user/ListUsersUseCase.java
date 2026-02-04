package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.mapper.UserDTOMapper;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersUseCase {

    private final UserOutputPort userOutputPort;
    private final UserDTOMapper mapper = new UserDTOMapper();

    public ListUsersUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public List<UserDTO> execute() {
        return userOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
