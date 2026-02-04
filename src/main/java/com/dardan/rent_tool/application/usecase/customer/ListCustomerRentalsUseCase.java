package com.dardan.rent_tool.application.usecase.customer;

import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListCustomerRentalsUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public ListCustomerRentalsUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public List<RentalDTO> execute(UUID customerId) {
        return rentalOutputPort.findByCustomerId(customerId).stream()
            .map(mapper::toDTO)
            .toList();
    }
}
