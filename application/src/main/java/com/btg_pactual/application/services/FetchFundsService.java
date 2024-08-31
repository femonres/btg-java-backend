package com.btg_pactual.application.services;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.mapper.FundMapper;
import com.btg_pactual.domain.services.FundService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FetchFundsService {
    private final FundService fundService;

    public FetchFundsService(FundService fundService) {
        this.fundService = fundService;
    }

    public List<FundDTO> fetchFunds() {
        return fundService.getAllFunds()
            .stream().map(FundMapper::toDTO).collect(Collectors.toList());
    }

}
