package com.btg_pactual.infrastructure.dao;

import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.FundCategory;

public class FundDAO {
    public static Item toDynamoItem(Fund fund) {
        return new Item()
            .withPrimaryKey("PK", "FUND#" + fund.getId())
            .withString("Name", fund.getName())
            .withNumber("MinAmount", fund.getMinAmount().getValue())
            .withString("Category", fund.getCategory().getCategory());
    }

    public static Fund fromDynamoItem(Item item) {
        return new Fund(
            Integer.parseInt(item.getString("PK").split("#")[1]),
            item.getString("Name"),
            new Amount(item.getInt("MinAmount")),
            new FundCategory(item.getString("Category"))
        );
    }
}
