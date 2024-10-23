package io.github.bzanf.cardmicroservice.domain.repositories;

import io.github.bzanf.cardmicroservice.domain.entities.Card;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    List<Card> findByIncomeLessThanEqual(BigDecimal income);
    Optional<Card> findById(Long id);
}
