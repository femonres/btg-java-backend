package com.btg_pactual.domain.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(int userId) {
        super(String.format("Usuario no encontrado. ID: %d", userId));
    }
}
