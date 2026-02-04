package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetRentalUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public GetRentalUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public RentalDTO execute(UUID id) {
        return rentalOutputPort.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));
    }
}
