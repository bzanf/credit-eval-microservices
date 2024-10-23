package io.github.bzanf.cardmicroservice.application.services;

import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;
import io.github.bzanf.cardmicroservice.domain.repositories.ClientCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    @Transactional
    public ClientCard save(ClientCard clientCard) {
        return repository.save(clientCard);
    }

    public List<ClientCard> findByIdCode(String idCode) {
        return repository.findByIdCode(idCode);
    }

}
