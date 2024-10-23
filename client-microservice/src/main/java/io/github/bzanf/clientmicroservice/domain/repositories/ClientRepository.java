package io.github.bzanf.clientmicroservice.domain.repositories;

import io.github.bzanf.clientmicroservice.domain.entities.Client;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByIdCode(String idCode);
    Client save(Client client);
}
