package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.repositories.FundRepository;
import com.btg_pactual.infrastructure.dao.FundDAO;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@Service
@RequiredArgsConstructor
public class DynamoDBFundRepositoryImpl implements FundRepository {

    private final DynamoDbClient dbClient;
    private String tableName = "FundTable";

    @Override
    public List<Fund> getAllFunds() {
        try {
            ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();
            ScanResponse response = dbClient.scan(scanRequest);
            return response.items().stream()
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
            Map<String, AttributeValue> keyToGet = new HashMap<>();
            keyToGet.put("PK", AttributeValue.builder()
                .s(String.format("FUND#%d", id))
                .build());

            GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(keyToGet)
                .build();

            GetItemResponse response = dbClient.getItem(request);
            if (response.hasItem()) {
                return Optional.of(FundDAO.fromDynamoItem(response.item()));
            }
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
}
