package com.btg_pactual.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.btg_pactual.domain.model.Fund;

public interface FundRepository {
    
    List<Fund> getAllFunds();

    Optional<Fund> getFundById(int id);
}
