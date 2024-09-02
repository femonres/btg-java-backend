package com.btg_pactual.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundDTO {
    private int id;
    private String name;
    private int minAmount;
    private String category;
}
