package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.repositories.FundRepository;
import com.btg_pactual.infrastructure.dao.FundDAO;

public class DynamoDBFundRepositoryImpl implements FundRepository {

    private final Table table;

    public DynamoDBFundRepositoryImpl(DynamoDB dynamoDB, String tableName) {
        this.table = dynamoDB.getTable(tableName);
    }

    @Override
    public List<Fund> getAllFunds() {
        try {
            var result = table.scan();
            return result.getItems().stream()
                .map(FundDAO::fromDynamoItem)
                .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Optional<Fund> getFundById(int id) {
        try {
            var item = table.getItem("PK", "FUND#" + fundId);
            if (item == null) {
                return Optional.empty();
            }
            return Optional.of(FundDAO.fromDynamoItem(item));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
}
