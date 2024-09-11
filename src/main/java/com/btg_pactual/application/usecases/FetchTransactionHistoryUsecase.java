package com.btg_pactual.application.usecases;

import java.util.List;

import com.btg_pactual.application.dto.TransactionDTO;

public interface FetchTransactionHistoryUsecase extends UseCase<Integer, List<TransactionDTO>> {}
