package io.github.bzanf.clientmicroservice.infrastructure.persistence;

import io.github.bzanf.clientmicroservice.domain.entities.Client;
import io.github.bzanf.clientmicroservice.domain.repositories.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<Client, Long>, ClientRepository {
}
