package com.dardan.rent_tool.domain.model.valueobject;

import java.util.Objects;

public final class Email {
    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("Email es obligatorio.");
        }
        String normalized = raw.trim().toLowerCase();
        if (normalized.isEmpty() || !normalized.contains("@") || normalized.contains(" ")) {
            throw new IllegalArgumentException("Email no es valido.");
        }
        return new Email(normalized);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
