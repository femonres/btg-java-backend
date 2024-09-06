package com.btg_pactual.application.mapper;

import java.time.format.DateTimeFormatter;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.domain.model.Transaction;

public class TransactionMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId().getId());
        dto.setFundId(transaction.getFund().getId());
        dto.setFundName(transaction.getFund().getName());
        dto.setAmount(transaction.getAmount().getValue());
        dto.setTimestamp(FORMATTER.format(transaction.getTimestamp()));
        dto.setTransactionType(transaction.getTransactionType());
        return dto;
    }
}
