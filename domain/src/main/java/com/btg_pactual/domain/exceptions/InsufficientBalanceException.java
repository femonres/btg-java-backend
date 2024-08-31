package com.btg_pactual.domain.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String fundName) {
        super(String.format("No tiene saldo disponible para vincularse al fondo: %s", fundName));
    }
}
