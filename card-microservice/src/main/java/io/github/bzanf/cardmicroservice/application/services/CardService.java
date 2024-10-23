package io.github.bzanf.cardmicroservice.application.services;

import io.github.bzanf.cardmicroservice.domain.entities.Card;
import io.github.bzanf.cardmicroservice.domain.exceptions.ObjectNotFoundException;
import io.github.bzanf.cardmicroservice.domain.repositories.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card save(Card card) {
        return repository.save(card);
    }

    public List<Card> findCardsWithIncomeLessThanOrEqual(Long income) {
        BigDecimal incomeThreshold = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeThreshold);
    }

    public Card findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Card not found with id: " + id));
    }

}
