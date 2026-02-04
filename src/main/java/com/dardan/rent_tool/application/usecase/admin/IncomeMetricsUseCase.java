package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.IncomeMetricsDTO;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncomeMetricsUseCase {

    private final PaymentOutputPort paymentOutputPort;

    public IncomeMetricsUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    public IncomeMetricsDTO execute() {
        BigDecimal total = paymentOutputPort.sumAmountByStatus(PaymentStatus.PAID);
        return new IncomeMetricsDTO(total);
    }
}
