package com.dardan.rent_tool.domain.model.valueobject;

import java.util.Objects;

public final class PasswordHash {
    private final String value;

    private PasswordHash(String value) {
        this.value = value;
    }

    public static PasswordHash ofHashed(String hashed) {
        if (hashed == null || hashed.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash es obligatorio.");
        }
        return new PasswordHash(hashed.trim());
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PasswordHash)) {
            return false;
        }
        PasswordHash that = (PasswordHash) o;
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
