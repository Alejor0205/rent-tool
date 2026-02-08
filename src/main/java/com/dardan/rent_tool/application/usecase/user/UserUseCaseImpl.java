package com.dardan.rent_tool.application.usecase.user;

import com.dardan.rent_tool.application.command.CreateUserCommand;
import com.dardan.rent_tool.application.command.UpdateUserCommand;
import com.dardan.rent_tool.application.dto.UserDTO;
import com.dardan.rent_tool.application.port.in.UserUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserUseCaseImpl(CreateUserUseCase createUserUseCase,
                           UpdateUserUseCase updateUserUseCase,
                           GetUserUseCase getUserUseCase,
                           ListUsersUseCase listUsersUseCase,
                           DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public UserDTO create(CreateUserCommand command) {
        return createUserUseCase.execute(command);
    }

    @Override
    public UserDTO update(UpdateUserCommand command) {
        return updateUserUseCase.execute(command);
    }

    @Override
    public UserDTO get(UUID id) {
        return getUserUseCase.execute(id);
    }

    @Override
    public List<UserDTO> list() {
        return listUsersUseCase.execute();
    }

    @Override
    public void delete(UUID id) {
        deleteUserUseCase.execute(id);
    }
}
