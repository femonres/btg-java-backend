package com.btg_pactual.application.dto;

import com.btg_pactual.domain.value_objects.Amount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundDTO {
    private int id;
    private String name;
    private Amount minAmount;
    private String category;
}
