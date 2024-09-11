package com.btg_pactual.application.usecases;

public interface UseCase<I, O> {
    O execute(I input);
}
