package com.btg_pactual.domain.exceptions;

public class FundNotFoundException extends RuntimeException {
    public FundNotFoundException(int fundId) {
        super(String.format("Fondo no encontrado. ID: %d", fundId));
    }
}