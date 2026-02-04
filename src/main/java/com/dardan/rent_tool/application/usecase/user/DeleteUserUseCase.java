package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserUseCase {

    private final UserOutputPort userOutputPort;

    public DeleteUserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public void execute(UUID id) {
        if (userOutputPort.findById(id).isEmpty()) {
            throw new NotFoundException("userId", "El usuario no existe.");
        }
        userOutputPort.deleteById(id);
    }
}
