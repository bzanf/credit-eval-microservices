package io.github.bzanf.cardmicroservice.domain.repositories;

import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;

import java.util.List;

public interface ClientCardRepository {
    ClientCard save(ClientCard card);
    List<ClientCard> findByIdCode(String idCode);
}
