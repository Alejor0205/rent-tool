package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

public class PayRentalRequest {
    private String method;

    public PayRentalRequest() {
    }

    public PayRentalRequest(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
