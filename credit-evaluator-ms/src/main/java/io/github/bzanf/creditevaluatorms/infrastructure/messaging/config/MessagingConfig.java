package io.github.bzanf.creditevaluatorms.infrastructure.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Value("${mq.queues.card-issuance}")
    private String cardIssuanceQueueName;

    @Bean
    public Queue cardIssuanceQueue() {
        return new Queue(cardIssuanceQueueName, true);
    }
}
