package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.command.CreateRentalCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CreateRentalUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final ToolOutputPort toolOutputPort;
    private final UserOutputPort userOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public CreateRentalUseCase(RentalOutputPort rentalOutputPort,
                               ToolOutputPort toolOutputPort,
                               UserOutputPort userOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
        this.toolOutputPort = toolOutputPort;
        this.userOutputPort = userOutputPort;
    }

    public RentalDTO execute(CreateRentalCommand command) {
        if (command.getToolId() == null || command.getCustomerId() == null) {
            throw new ValidationException("rental", "toolId y customerId son obligatorios.");
        }
        LocalDate startDate = command.getStartDate();
        LocalDate endDate = command.getEndDate();
        if (startDate == null || endDate == null) {
            throw new ValidationException("rental.dates", "Las fechas de inicio y fin son obligatorias.");
        }
        if (endDate.isBefore(startDate)) {
            throw new ValidationException("rental.dates", "La fecha de fin debe ser posterior a la de inicio.");
        }
        RentTool tool = toolOutputPort.findById(command.getToolId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));
        if (tool.getStatus() != ToolStatus.AVAILABLE) {
            throw new ValidationException("tool.status", "La herramienta no está disponible.");
        }
        if (userOutputPort.findById(command.getCustomerId()).isEmpty()) {
            throw new NotFoundException("customerId", "El cliente no existe.");
        }
        if (command.getProviderId() != null && userOutputPort.findById(command.getProviderId()).isEmpty()) {
            throw new NotFoundException("providerId", "El proveedor no existe.");
        }
        boolean hasOverlap = rentalOutputPort.existsOverlap(
            command.getToolId(),
            startDate,
            endDate,
            List.of(RentalStatus.REQUESTED, RentalStatus.ACCEPTED, RentalStatus.ACTIVE)
        );
        if (hasOverlap) {
            throw new ValidationException("rental.dates", "La herramienta ya tiene reservas en ese rango.");
        }

        Rental rental = new Rental(
            null,
            command.getToolId(),
            command.getCustomerId(),
            command.getProviderId(),
            command.getStartDate(),
            command.getEndDate(),
            command.getTotalAmount(),
            RentalStatus.REQUESTED,
            OffsetDateTime.now()
        );

        return mapper.toDTO(rentalOutputPort.save(rental));
    }
}
