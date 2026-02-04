package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.CreateRentalCommand;
import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.usecase.customer.ListCustomerRentalsUseCase;
import com.dardan.rent_tool.application.usecase.payment.CreatePaymentUseCase;
import com.dardan.rent_tool.application.usecase.rental.CreateRentalUseCase;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.CreateCustomerRentalRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.PayRentalRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.PaymentResponse;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.RentalResponse;
import com.dardan.rent_tool.infrastructure.security.SecurityUtils;
import com.dardan.rent_tool.application.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ListCustomerRentalsUseCase listCustomerRentalsUseCase;
    private final CreateRentalUseCase createRentalUseCase;
    private final CreatePaymentUseCase createPaymentUseCase;

    public CustomerController(ListCustomerRentalsUseCase listCustomerRentalsUseCase,
                              CreateRentalUseCase createRentalUseCase,
                              CreatePaymentUseCase createPaymentUseCase) {
        this.listCustomerRentalsUseCase = listCustomerRentalsUseCase;
        this.createRentalUseCase = createRentalUseCase;
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @GetMapping("/rentals")
    public List<RentalResponse> history() {
        UUID customerId = requireUserId();
        return listCustomerRentalsUseCase.execute(customerId).stream()
            .map(this::toResponse)
            .toList();
    }

    @PostMapping("/rentals")
    public ResponseEntity<RentalResponse> create(@RequestBody CreateCustomerRentalRequest request) {
        UUID customerId = requireUserId();
        RentalDTO dto = createRentalUseCase.execute(new CreateRentalCommand(
            request.getToolId(),
            customerId,
            request.getProviderId(),
            request.getStartDate(),
            request.getEndDate(),
            request.getTotalAmount()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(dto));
    }

    @PostMapping("/rentals/{id}/pay")
    public PaymentResponse pay(@PathVariable UUID id, @RequestBody PayRentalRequest request) {
        UUID customerId = requireUserId();
        PaymentDTO dto = createPaymentUseCase.execute(id, customerId, request.getMethod());
        return toResponse(dto);
    }

    private UUID requireUserId() {
        UUID userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new ValidationException("auth", "Usuario no autenticado.");
        }
        return userId;
    }

    private RentalResponse toResponse(RentalDTO dto) {
        return new RentalResponse(
            dto.getId(),
            dto.getToolId(),
            dto.getCustomerId(),
            dto.getProviderId(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getTotalAmount(),
            dto.getStatus(),
            dto.getCreatedAt()
        );
    }

    private PaymentResponse toResponse(PaymentDTO dto) {
        return new PaymentResponse(
            dto.getId(),
            dto.getRentalId(),
            dto.getAmount(),
            dto.getStatus(),
            dto.getMethod(),
            dto.getCreatedAt()
        );
    }
}
