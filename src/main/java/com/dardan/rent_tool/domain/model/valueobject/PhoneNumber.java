package com.dardan.rent_tool.domain.model.valueobject;

import java.util.Objects;

public final class PhoneNumber {
    private final String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("Telefono es obligatorio.");
        }
        String normalized = raw.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("Telefono es obligatorio.");
        }
        String cleaned = normalized.replace(" ", "").replace("-", "");
        if (!cleaned.matches("[+]?\\d{7,15}")) {
            throw new IllegalArgumentException("Telefono no es valido.");
        }
        return new PhoneNumber(cleaned);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return value.equals(that.value);
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
