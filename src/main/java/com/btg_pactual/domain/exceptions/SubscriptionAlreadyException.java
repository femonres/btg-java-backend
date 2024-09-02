package com.btg_pactual.domain.exceptions;

public class SubscriptionAlreadyException extends RuntimeException {
    public SubscriptionAlreadyException(String fundName) {
        super(String.format("El cliente ya está suscrito al fondo: %s.", fundName));
    }
}
