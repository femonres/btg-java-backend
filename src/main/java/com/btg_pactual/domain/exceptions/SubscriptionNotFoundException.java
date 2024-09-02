package com.btg_pactual.domain.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String fundName) {
        super(String.format("No hay suscripción activa para el fondo: %s", fundName));
    }
}
