package com.btg_pactual.domain.model;

import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.FundCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fund {
    private int id;
    private String name;
    private Amount minAmount;
    private FundCategory category;

    public boolean canInvest(Amount amount) {
        return this.minAmount.getValue() <= amount.getValue();
    }
}
