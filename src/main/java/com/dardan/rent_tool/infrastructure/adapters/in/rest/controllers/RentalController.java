package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.ConfirmReturnCommand;
import com.dardan.rent_tool.application.command.CreateRentalCommand;
import com.dardan.rent_tool.application.command.UpdateRentalStatusCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.usecase.rental.AcceptRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.ConfirmReturnUseCase;
import com.dardan.rent_tool.application.usecase.rental.CreateRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.DeleteRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.GenerateInvoiceUseCase;
import com.dardan.rent_tool.application.usecase.rental.GetRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.RejectRentalUseCase;
import com.dardan.rent_tool.application.usecase.rental.UpdateRentalUseCase;
import com.dardan.rent_tool.application.port.out.InvoiceOutputPort;
import com.dardan.rent_tool.domain.model.entity.Invoice;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.ConfirmReturnRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.CreateRentalRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.UpdateRentalRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.RentalResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final CreateRentalUseCase createRentalUseCase;
    private final AcceptRentalUseCase acceptRentalUseCase;
    private final RejectRentalUseCase rejectRentalUseCase;
    private final ConfirmReturnUseCase confirmReturnUseCase;
    private final GenerateInvoiceUseCase generateInvoiceUseCase;
    private final InvoiceOutputPort invoiceOutputPort;
    private final GetRentalUseCase getRentalUseCase;
    private final UpdateRentalUseCase updateRentalUseCase;
    private final DeleteRentalUseCase deleteRentalUseCase;

    public RentalController(CreateRentalUseCase createRentalUseCase,
                            AcceptRentalUseCase acceptRentalUseCase,
                            RejectRentalUseCase rejectRentalUseCase,
                            ConfirmReturnUseCase confirmReturnUseCase,
                            GenerateInvoiceUseCase generateInvoiceUseCase,
                            InvoiceOutputPort invoiceOutputPort,
                            GetRentalUseCase getRentalUseCase,
                            UpdateRentalUseCase updateRentalUseCase,
                            DeleteRentalUseCase deleteRentalUseCase) {
        this.createRentalUseCase = createRentalUseCase;
        this.acceptRentalUseCase = acceptRentalUseCase;
        this.rejectRentalUseCase = rejectRentalUseCase;
        this.confirmReturnUseCase = confirmReturnUseCase;
        this.generateInvoiceUseCase = generateInvoiceUseCase;
        this.invoiceOutputPort = invoiceOutputPort;
        this.getRentalUseCase = getRentalUseCase;
        this.updateRentalUseCase = updateRentalUseCase;
        this.deleteRentalUseCase = deleteRentalUseCase;
    }

    @PostMapping
    public ResponseEntity<RentalResponse> create(@RequestBody CreateRentalRequest request) {
        RentalDTO dto = createRentalUseCase.execute(new CreateRentalCommand(
            request.getToolId(),
            request.getCustomerId(),
            request.getProviderId(),
            request.getStartDate(),
            request.getEndDate(),
            request.getTotalAmount()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(dto));
    }

    @GetMapping("/{id}")
    public RentalResponse get(@PathVariable UUID id) {
        return toResponse(getRentalUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public RentalResponse update(@PathVariable UUID id, @RequestBody UpdateRentalRequest request) {
        return toResponse(updateRentalUseCase.execute(
            new com.dardan.rent_tool.application.command.UpdateRentalCommand(
                id,
                request.getStartDate(),
                request.getEndDate(),
                request.getTotalAmount()
            )
        ));
    }

    @PatchMapping("/{id}/accept")
    public RentalResponse accept(@PathVariable UUID id) {
        return toResponse(acceptRentalUseCase.execute(new UpdateRentalStatusCommand(id, RentalStatus.ACCEPTED)));
    }

    @PatchMapping("/{id}/reject")
    public RentalResponse reject(@PathVariable UUID id) {
        return toResponse(rejectRentalUseCase.execute(new UpdateRentalStatusCommand(id, RentalStatus.REJECTED)));
    }

    @PatchMapping("/{id}/return")
    public RentalResponse confirmReturn(@PathVariable UUID id, @RequestBody ConfirmReturnRequest request) {
        return toResponse(confirmReturnUseCase.execute(
            new ConfirmReturnCommand(id, request.isDamaged(), request.getDamageDescription())
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteRentalUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity<byte[]> invoice(@PathVariable UUID id) {
        byte[] pdf = invoiceOutputPort.findByRentalId(id)
            .map(Invoice::getPdfData)
            .orElseGet(() -> {
                byte[] generated = generateInvoiceUseCase.execute(id);
                invoiceOutputPort.save(new Invoice(null, id, generated, java.time.OffsetDateTime.now()));
                return generated;
            });
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice-" + id + ".pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
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
