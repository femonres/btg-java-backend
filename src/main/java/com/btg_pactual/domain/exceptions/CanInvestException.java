package com.btg_pactual.domain.exceptions;

import com.btg_pactual.domain.value_objects.Amount;

public class CanInvestException extends RuntimeException {
    public CanInvestException(String fundName, Amount minAmount) {
        super(String.format("El monto con el cual desea vincularse al fondo: %s, es inferior al mínimo requerido: %d", fundName, minAmount.getValue()));
    }
}
