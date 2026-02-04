package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.usecase.auth.RegisterCustomerUseCase;
import com.dardan.rent_tool.domain.model.entity.User;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.LoginRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.RegisterRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.AuthResponse;
import com.dardan.rent_tool.infrastructure.security.CustomUserDetails;
import com.dardan.rent_tool.infrastructure.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(RegisterCustomerUseCase registerCustomerUseCase,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.registerCustomerUseCase = registerCustomerUseCase;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = registerCustomerUseCase.execute(
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            request.getPassword()
        );
        CustomUserDetails details = new CustomUserDetails(
            user.getId(),
            user.getEmail(),
            user.getPasswordHash(),
            user.getRole()
        );
        String token = jwtService.generateToken(details);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AuthResponse(token, user.getId(), user.getRole()));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token, userDetails.getId(),
            com.dardan.rent_tool.domain.model.enumm.RoleType.valueOf(userDetails.getRole()));
    }
}
