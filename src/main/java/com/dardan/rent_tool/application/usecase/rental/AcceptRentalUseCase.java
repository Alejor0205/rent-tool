package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.command.UpdateRentalStatusCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcceptRentalUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final ToolOutputPort toolOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public AcceptRentalUseCase(RentalOutputPort rentalOutputPort, ToolOutputPort toolOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
        this.toolOutputPort = toolOutputPort;
    }

    public RentalDTO execute(UpdateRentalStatusCommand command) {
        Rental rental = rentalOutputPort.findById(command.getRentalId())
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));
        if (rental.getStatus() != RentalStatus.REQUESTED) {
            throw new ValidationException("rental.status", "Solo se pueden aceptar reservas en estado REQUESTED.");
        }
        RentTool tool = toolOutputPort.findById(rental.getToolId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));
        if (tool.getStatus() != ToolStatus.AVAILABLE) {
            throw new ValidationException("tool.status", "La herramienta no está disponible.");
        }
        boolean hasOverlap = rentalOutputPort.existsOverlapExcluding(
            rental.getToolId(),
            rental.getId(),
            rental.getStartDate(),
            rental.getEndDate(),
            List.of(RentalStatus.ACCEPTED, RentalStatus.ACTIVE)
        );
        if (hasOverlap) {
            throw new ValidationException("rental.dates", "La herramienta ya tiene una reserva activa en ese rango.");
        }

        rental.setStatus(RentalStatus.ACCEPTED);
        tool.setStatus(ToolStatus.RENTED);
        toolOutputPort.save(tool);

        return mapper.toDTO(rentalOutputPort.save(rental));
    }
}
