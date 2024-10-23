package io.github.bzanf.creditevaluatorms.infrastructure.messaging.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bzanf.creditevaluatorms.application.commands.RequestCardIssuance;
import io.github.bzanf.creditevaluatorms.application.exceptions.DataWithInvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void requestCardIssuance(RequestCardIssuance request) {
        String json = toJson(request);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String toJson(RequestCardIssuance request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new DataWithInvalidFormatException("Error converting request data to JSON.");
        }

    }


}