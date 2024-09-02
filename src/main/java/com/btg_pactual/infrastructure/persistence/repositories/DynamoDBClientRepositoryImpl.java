package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.repositories.ClientRepository;
import com.btg_pactual.infrastructure.dao.ClientDAO;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@Service
@RequiredArgsConstructor
public class DynamoDBClientRepositoryImpl implements ClientRepository {

    private final DynamoDbClient dbClient;
    private String tableName = "ClientTable";

    @Override
    public List<Client> getAllClients() {
        try {
            ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .build();
            ScanResponse response = dbClient.scan(scanRequest);
            
            return response.items().stream()
                .map(ClientDAO::fromDynamoItem)
                .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly
            return List.of();
        }
    }

    @Override
    public Optional<Client> getClientById(int id) {
        try {
            Map<String, AttributeValue> keyToGet = new HashMap<>();
            keyToGet.put("PK", AttributeValue.builder()
                .s(String.format("CLIENT#%d", id))
                .build());

            GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(keyToGet)
                .build();

            GetItemResponse response = dbClient.getItem(request);
            if (response.hasItem()) {
                return Optional.of(ClientDAO.fromDynamoItem(response.item()));
            }
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly
            return Optional.empty();
        }
    }

    @Override
    public void saveClient(Client client) {
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(ClientDAO.toDynamoItem(client))
                .build();

        dbClient.putItem(putItemRequest);
    }
    
}
