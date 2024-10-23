package io.github.bzanf.clientmicroservice.application.services;

import io.github.bzanf.clientmicroservice.domain.entities.Client;
import io.github.bzanf.clientmicroservice.domain.exceptions.ObjectNotFoundException;
import io.github.bzanf.clientmicroservice.domain.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public Client getByIdCode(String idCode) {
        return repository
                .findByIdCode(idCode)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found with idCode: " + idCode));
    }

}
