package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.command.ConfirmReturnCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.DamageReportOutputPort;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.DamageReport;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ConfirmReturnUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final ToolOutputPort toolOutputPort;
    private final DamageReportOutputPort damageReportOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public ConfirmReturnUseCase(RentalOutputPort rentalOutputPort,
                                ToolOutputPort toolOutputPort,
                                DamageReportOutputPort damageReportOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
        this.toolOutputPort = toolOutputPort;
        this.damageReportOutputPort = damageReportOutputPort;
    }

    public RentalDTO execute(ConfirmReturnCommand command) {
        Rental rental = rentalOutputPort.findById(command.getRentalId())
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));

        rental.setStatus(RentalStatus.RETURNED);
        RentTool tool = toolOutputPort.findById(rental.getToolId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));

        if (command.isDamaged()) {
            tool.setStatus(ToolStatus.MAINTENANCE);
            DamageReport report = new DamageReport(
                null,
                rental.getId(),
                rental.getToolId(),
                command.getDamageDescription() == null ? "Daño reportado." : command.getDamageDescription(),
                false,
                OffsetDateTime.now()
            );
            damageReportOutputPort.save(report);
        } else {
            tool.setStatus(ToolStatus.AVAILABLE);
        }

        toolOutputPort.save(tool);
        return mapper.toDTO(rentalOutputPort.save(rental));
    }
}
