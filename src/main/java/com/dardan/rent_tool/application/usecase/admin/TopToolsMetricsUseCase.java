package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.ToolRentCountDTO;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopToolsMetricsUseCase {

    private final RentalOutputPort rentalOutputPort;

    public TopToolsMetricsUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public List<ToolRentCountDTO> execute() {
        return rentalOutputPort.findTopRentedTools().stream()
            .map(item -> new ToolRentCountDTO(item.getToolId(), item.getTotal()))
            .toList();
    }
}
