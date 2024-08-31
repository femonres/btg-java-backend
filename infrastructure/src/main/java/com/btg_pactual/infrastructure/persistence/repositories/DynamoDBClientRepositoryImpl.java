package com.btg_pactual.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.repositories.ClientRepository;
import com.btg_pactual.infrastructure.dao.ClientDAO;

public class DynamoDBClientRepositoryImpl implements ClientRepository {

    private final Table table;

    public DynamoDBClientRepositoryImpl(DynamoDB dynamoDB, String tableName) {
        this.table = dynamoDB.getTable(tableName);
    }

    @Override
    public List<Client> getAllClients() {
        try {
            var result = table.scan();
            return result.getItems().stream()
                .map(ClientDAO::fromDynamoItem)
                .toList();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly
            return List.of();
        }
    }

    @Override
    public Optional<Client> getClientById(int id) {
        try {
            var item = table.getItem("PK", "CLIENT#" + clientId);
            if (item == null) {
                return Optional.empty();
            }
            return Optional.of(ClientDAO.fromDynamoItem(item));
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly
            return Optional.empty();
        }
    }

    @Override
    public void saveClient(Client client) {
        var item = ClientDAO.toDynamoItem(client);
        table.putItem(item);
    }
    
}
