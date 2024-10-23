package io.github.bzanf.creditevaluatorms.application.services;

import feign.FeignException;
import io.github.bzanf.creditevaluatorms.application.commands.RequestCardIssuance;
import io.github.bzanf.creditevaluatorms.application.exceptions.MicroserviceCommunicationException;
import io.github.bzanf.creditevaluatorms.application.responses.CardByIncomeResponse;
import io.github.bzanf.creditevaluatorms.application.responses.CardResponse;
import io.github.bzanf.creditevaluatorms.application.responses.ClientResponse;
import io.github.bzanf.creditevaluatorms.application.responses.CreditEvaluatorResponse;
import io.github.bzanf.creditevaluatorms.domain.entities.Card;
import io.github.bzanf.creditevaluatorms.domain.entities.Client;
import io.github.bzanf.creditevaluatorms.domain.entities.ClientCard;
import io.github.bzanf.creditevaluatorms.domain.exceptions.ObjectNotFoundException;
import io.github.bzanf.creditevaluatorms.infrastructure.clients.CardServiceClient;
import io.github.bzanf.creditevaluatorms.infrastructure.clients.ClientServiceClient;
import io.github.bzanf.creditevaluatorms.infrastructure.messaging.producers.CardIssuanceProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientServiceClient clientService;
    private final CardServiceClient cardService;
    private final CardIssuanceProducer cardIssuanceProducer;

    public ClientCard fetchClientCard(String idCode) {
        try {
            ResponseEntity<ClientResponse> clientResponse = clientService.findByIdCode(idCode);
            ResponseEntity<List<CardResponse>> cardResponse = cardService.findByClientIdCode(idCode);

            return ClientCard
                    .builder()
                    .client(Objects.requireNonNull(clientResponse.getBody()).toModel())
                    .cards(Objects.requireNonNull(cardResponse.getBody())
                            .stream()
                            .map(CardResponse::toModel)
                            .toList())
                    .build();
        } catch (FeignException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status())
                throw new ObjectNotFoundException("Client not found with idCode: " + idCode);

            throw new MicroserviceCommunicationException("Error communicating with client/card microservice.");
        }
    }

    public CreditEvaluatorResponse evaluateCredit(String idCode, Long income) {
        try {
            ResponseEntity<ClientResponse> clientResponse = clientService.findByIdCode(idCode);
            ResponseEntity<List<CardByIncomeResponse>> cardsResponse = cardService.findCardsWithIncomeLessThanOrEqual(income);

            List<Card> cards = Objects.requireNonNull(cardsResponse.getBody())
                    .stream()
                    .map(CardByIncomeResponse::toModel)
                    .toList();

            Client client = Objects.requireNonNull(clientResponse.getBody()).toModel();

            List<Card> approvedCards = cards.stream().map(card -> {
                BigDecimal creditLimit = card.getCreditLimit();
                BigDecimal age = BigDecimal.valueOf(client.getAge());
                BigDecimal factor = age.divide(BigDecimal.valueOf(10), RoundingMode.HALF_UP);
                BigDecimal approvedLimit = factor.multiply(creditLimit);

                return new Card(card.getName(), card.getNetwork(), approvedLimit);
            }).toList();

            return new CreditEvaluatorResponse(approvedCards);

        } catch (FeignException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status())
                throw new ObjectNotFoundException("Client not found with idCode: " + idCode);

            throw new MicroserviceCommunicationException("Error communicating with client/card microservice.");
        }
    }

    public String requestCardIssuance(RequestCardIssuance request) {
        cardIssuanceProducer.requestCardIssuance(request);
        return UUID.randomUUID().toString();
    }

}
