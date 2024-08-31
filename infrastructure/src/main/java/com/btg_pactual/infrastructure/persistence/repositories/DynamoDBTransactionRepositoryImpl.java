package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Map;

import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.repositories.TransactionRepository;
import com.btg_pactual.infrastructure.dao.TransactionDAO;

public class DynamoDBTransactionRepositoryImpl implements TransactionRepository {

    private final Table table;

    public DynamoDBTransactionRepositoryImpl(DynamoDB dynamoDB, String tableName) {
        this.table = dynamoDB.getTable(tableName);
    }

    @Override
    public List<Transaction> getAllTransactionsByClientId(int clientId) {
        try {
            var query = new QueryRequest()
                .withTableName(table.getTableName())
                .withIndexName("ClientIDIndex")
                .withKeyConditionExpression("ClientID = :clientId")
                .withExpressionAttributeValues(Map.of(":clientId", new AttributeValue().withN(String.valueOf(clientId))));

            var result = table.query(query);
            return result.getItems().stream()
                .map(TransactionDAO::fromDynamoItem)
                .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        var item = TransactionDAO.toDynamoItem(transaction);
        table.putItem(item);
    }
    
}
