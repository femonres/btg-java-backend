package com.btg_pactual.application.mapper;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.domain.model.Fund;

public class FundMapper {
    
    public static FundDTO toDTO(Fund fund) {
        FundDTO dto = new FundDTO();
        dto.setId(fund.getId());
        dto.setName(fund.getName());
        dto.setMinAmount(fund.getMinAmount().getValue());
        dto.setCategory(fund.getCategory().getCategory());
        return dto;
    }
}
