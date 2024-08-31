package com.btg_pactual.domain.value_objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Amount {
    private final int value;

    public Amount(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.value = value;
    }

    public Amount add(Amount other) {
        return new Amount(this.value + other.getValue());
    }

    public Amount subtract(Amount other) {
        return new Amount(this.value - other.getValue());
    }

    @Override
    public String toString() {
        return "Amount(" + value + ")";
    }

    public Integer compareTo(Amount amount) {
        return Integer.compare(this.value, amount.getValue());
    }
}
