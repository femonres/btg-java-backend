package com.btg_pactual.domain.services;

import java.util.ArrayList;
import java.util.List;

import com.btg_pactual.domain.exceptions.ClientNotFoundException;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.repositories.ClientRepository;
import com.btg_pactual.domain.value_objects.Amount;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client getClientById(int id) {
        return clientRepository.getClientById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public void updateProfile(Client client) {
        clientRepository.saveClient(client);
    }

    public Client resetBalance(Client client) {
        client.setBalance(new Amount(0));
        client.setSubscriptions(new ArrayList<>());
        clientRepository.saveClient(client);
        return client;
    }
}
