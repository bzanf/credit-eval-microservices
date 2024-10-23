package io.github.bzanf.cardmicroservice.infrastructure.persistence;

import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;
import io.github.bzanf.cardmicroservice.domain.repositories.ClientCardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCardJpaRepository extends JpaRepository<ClientCard, Long>, ClientCardRepository {
}
