package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.UpdatePaymentCommand;
import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.usecase.payment.DeletePaymentUseCase;
import com.dardan.rent_tool.application.usecase.payment.GetPaymentUseCase;
import com.dardan.rent_tool.application.usecase.payment.UpdatePaymentUseCase;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.UpdatePaymentRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final GetPaymentUseCase getPaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final DeletePaymentUseCase deletePaymentUseCase;

    public PaymentController(GetPaymentUseCase getPaymentUseCase,
                             UpdatePaymentUseCase updatePaymentUseCase,
                             DeletePaymentUseCase deletePaymentUseCase) {
        this.getPaymentUseCase = getPaymentUseCase;
        this.updatePaymentUseCase = updatePaymentUseCase;
        this.deletePaymentUseCase = deletePaymentUseCase;
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable UUID id) {
        return toResponse(getPaymentUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public PaymentResponse update(@PathVariable UUID id, @RequestBody UpdatePaymentRequest request) {
        PaymentDTO dto = updatePaymentUseCase.execute(new UpdatePaymentCommand(
            id,
            request.getStatus(),
            request.getMethod()
        ));
        return toResponse(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deletePaymentUseCase.execute(id);
        return ResponseEntity.noContent().build();
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
