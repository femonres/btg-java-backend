package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.repositories.TransactionRepository;
import com.btg_pactual.infrastructure.dao.TransactionDAO;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

@Service
@RequiredArgsConstructor
public class DynamoDBTransactionRepositoryImpl implements TransactionRepository {

    private final DynamoDbClient dbClient;
    private String tableName = "TransactionTable";

    @Override
    public List<Transaction> getAllTransactionsByClientId(int clientId) {
        try {
            QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .indexName("ClientIDIndex") // Indice secundario
                .keyConditionExpression("ClientID = :clientId")
                .expressionAttributeValues(Map.of(
                    ":clientId", AttributeValue.builder().s(String.valueOf(clientId)).build()
                )).build();
            QueryResponse queryResponse = dbClient.query(queryRequest);

            return queryResponse.items().stream()
                .map(TransactionDAO::fromDynamoItem)
                .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(TransactionDAO.toDynamoItem(transaction))
                .build();

        dbClient.putItem(putItemRequest);
    }
    
}
