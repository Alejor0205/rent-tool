package com.dardan.rent_tool.domain.model.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(BigDecimal raw) {
        if (raw == null) {
            throw new IllegalArgumentException("Monto es obligatorio.");
        }
        if (raw.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Monto no puede ser negativo.");
        }
        BigDecimal normalized = raw.setScale(2, RoundingMode.HALF_UP);
        return new Money(normalized);
    }

    public BigDecimal amount() {
        return amount;
    }

    public Money add(Money other) {
        return Money.of(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return Money.of(this.amount.subtract(other.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }
}
