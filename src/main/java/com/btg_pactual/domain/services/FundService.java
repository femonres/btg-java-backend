package com.btg_pactual.domain.services;

import java.util.List;

import com.btg_pactual.domain.exceptions.FundNotFoundException;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.repositories.FundRepository;

public class FundService {
    private final FundRepository fundRepository;

    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<Fund> getAllFunds() {
        return fundRepository.getAllFunds();
    }

    public Fund getFundById(int id) {
        return fundRepository.getFundById(id).orElseThrow(() -> new FundNotFoundException(id));
    }

}
