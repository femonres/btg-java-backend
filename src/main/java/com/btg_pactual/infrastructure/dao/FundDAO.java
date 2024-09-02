package com.btg_pactual.infrastructure.dao;

import java.util.Map;

import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.FundCategory;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class FundDAO {
    public static Map<String, AttributeValue> toDynamoItem(Fund fund) {
        return Map.of(
            "PK", AttributeValue.builder().s("FUND#" + fund.getId()).build(),
            "Name", AttributeValue.builder().s(fund.getName()).build(),
            "MinAmount", AttributeValue.builder().n(String.valueOf(fund.getMinAmount().getValue())).build(),
            "Category", AttributeValue.builder().s(fund.getCategory().getCategory()).build()
        );
    }

    public static Fund fromDynamoItem(Map<String, AttributeValue> item) {
        Fund fund = new Fund();
        fund.setId(Integer.parseInt(item.get("PK").s().split("#")[1]));
        fund.setName(item.get("Name").s());
        fund.setMinAmount(new Amount(Integer.parseInt(item.get("MinAmount").n())));
        fund.setCategory(new FundCategory(item.get("Category").s()));
        return fund;
    }
}
