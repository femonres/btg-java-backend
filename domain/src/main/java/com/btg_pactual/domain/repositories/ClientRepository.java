package com.btg_pactual.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.btg_pactual.domain.model.Client;

public interface ClientRepository {

    List<Client> getAllClients();

    Optional<Client> getClientById(int id);

    void saveClient(Client client);
}
