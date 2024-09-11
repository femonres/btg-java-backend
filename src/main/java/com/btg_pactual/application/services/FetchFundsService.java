package com.btg_pactual.application.services;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.mapper.FundMapper;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.domain.services.FundService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchFundsService implements FetchFundsUsecase {
    private final FundService fundService;

    @Override
    public List<FundDTO> execute(Void input) {
        return fundService.getAllFunds()
            .stream().map(FundMapper::toDTO).collect(Collectors.toList());
    }

}
