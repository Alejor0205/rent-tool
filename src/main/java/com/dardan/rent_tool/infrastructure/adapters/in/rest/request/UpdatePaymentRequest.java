package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;

public class UpdatePaymentRequest {
    private PaymentStatus status;
    private String method;

    public UpdatePaymentRequest() {
    }

    public UpdatePaymentRequest(PaymentStatus status, String method) {
        this.status = status;
        this.method = method;
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
