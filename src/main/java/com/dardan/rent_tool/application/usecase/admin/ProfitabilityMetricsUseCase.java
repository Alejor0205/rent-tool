package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.ToolIncomeDTO;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitabilityMetricsUseCase {

    private final RentalOutputPort rentalOutputPort;

    public ProfitabilityMetricsUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public List<ToolIncomeDTO> execute() {
        return rentalOutputPort.findToolIncome().stream()
            .map(item -> new ToolIncomeDTO(item.getToolId(), item.getTotalIncome()))
            .toList();
    }
}
