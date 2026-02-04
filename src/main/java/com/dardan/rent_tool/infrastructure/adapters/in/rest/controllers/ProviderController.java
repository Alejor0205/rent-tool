package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.ConfirmReturnCommand;
import com.dardan.rent_tool.application.command.UpdateRentalStatusCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.usecase.rental.AcceptRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.ConfirmReturnUseCase;
import com.dardan.rent_tool.application.usecase.rental.RejectRentalUseCase;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.ConfirmReturnRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.RentalResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    private final AcceptRentalUseCase acceptRentalUseCase;
    private final RejectRentalUseCase rejectRentalUseCase;
    private final ConfirmReturnUseCase confirmReturnUseCase;

    public ProviderController(AcceptRentalUseCase acceptRentalUseCase,
                              RejectRentalUseCase rejectRentalUseCase,
                              ConfirmReturnUseCase confirmReturnUseCase) {
        this.acceptRentalUseCase = acceptRentalUseCase;
        this.rejectRentalUseCase = rejectRentalUseCase;
        this.confirmReturnUseCase = confirmReturnUseCase;
    }

    @PatchMapping("/rentals/{id}/accept")
    public RentalResponse accept(@PathVariable UUID id) {
        RentalDTO dto = acceptRentalUseCase.execute(new UpdateRentalStatusCommand(id, RentalStatus.ACCEPTED));
        return toResponse(dto);
    }

    @PatchMapping("/rentals/{id}/reject")
    public RentalResponse reject(@PathVariable UUID id) {
        RentalDTO dto = rejectRentalUseCase.execute(new UpdateRentalStatusCommand(id, RentalStatus.REJECTED));
        return toResponse(dto);
    }

    @PatchMapping("/rentals/{id}/return")
    public RentalResponse confirmReturn(@PathVariable UUID id, @RequestBody ConfirmReturnRequest request) {
        RentalDTO dto = confirmReturnUseCase.execute(
            new ConfirmReturnCommand(id, request.isDamaged(), request.getDamageDescription())
        );
        return toResponse(dto);
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
}
