package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRentalsUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public ListRentalsUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public List<RentalDTO> execute() {
        return rentalOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
