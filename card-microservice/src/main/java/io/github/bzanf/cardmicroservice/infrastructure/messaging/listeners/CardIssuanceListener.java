package io.github.bzanf.cardmicroservice.infrastructure.messaging.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bzanf.cardmicroservice.application.responses.CardIssuanceMessage;
import io.github.bzanf.cardmicroservice.application.services.CardService;
import io.github.bzanf.cardmicroservice.application.services.ClientCardService;
import io.github.bzanf.cardmicroservice.domain.entities.Card;
import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardIssuanceListener {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void onCardIssuanceMessageReceived(@Payload String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CardIssuanceMessage message = mapper.readValue(payload, CardIssuanceMessage.class);
            Card card = cardService.findById(message.getCardId());
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setIdCode(message.getClientIdCode());
            clientCard.setCreditLimit(message.getCreditLimit());
            clientCardService.save(clientCard);

        } catch (JsonProcessingException e) {
            log.error("Error receiving card issuance message from RabbitMQ: {}", e.getMessage());
        }
    }
}
