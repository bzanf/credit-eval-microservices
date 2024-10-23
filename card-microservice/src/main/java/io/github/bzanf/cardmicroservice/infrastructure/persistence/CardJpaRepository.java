package io.github.bzanf.cardmicroservice.infrastructure.persistence;

import io.github.bzanf.cardmicroservice.domain.entities.Card;
import io.github.bzanf.cardmicroservice.domain.repositories.CardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Long>, CardRepository {
}
