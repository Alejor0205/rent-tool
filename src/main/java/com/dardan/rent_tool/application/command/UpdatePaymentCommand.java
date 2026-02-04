package com.dardan.rent_tool.application.command;

import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;

import java.util.UUID;

public class UpdatePaymentCommand {
    private UUID paymentId;
    private PaymentStatus status;
    private String method;

    public UpdatePaymentCommand() {
    }

    public UpdatePaymentCommand(UUID paymentId, PaymentStatus status, String method) {
        this.paymentId = paymentId;
        this.status = status;
        this.method = method;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
