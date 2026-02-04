package com.dardan.rent_tool.application.usecase.auth;

import com.dardan.rent_tool.application.exception.ConflictException;
import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.domain.model.enumm.RoleType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerUseCase {

    private final UserOutputPort userOutputPort;
    private final PasswordEncoder passwordEncoder;

    public RegisterCustomerUseCase(UserOutputPort userOutputPort, PasswordEncoder passwordEncoder) {
        this.userOutputPort = userOutputPort;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String fullName, String email, String phone, String password) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new ValidationException("user.fullName", "El nombre completo es obligatorio.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new ValidationException("user.email", "El email no es válido.");
        }
        if (password == null || password.length() < 6) {
            throw new ValidationException("user.password", "La contraseña debe tener al menos 6 caracteres.");
        }
        if (userOutputPort.existsByEmailIgnoreCase(email.trim())) {
            throw new ConflictException("user.email", "El email ya está registrado.");
        }

        User user = new User(
            null,
            fullName.trim(),
            email.trim(),
            phone,
            RoleType.CUSTOMER,
            passwordEncoder.encode(password)
        );
        return userOutputPort.save(user);
    }
}
