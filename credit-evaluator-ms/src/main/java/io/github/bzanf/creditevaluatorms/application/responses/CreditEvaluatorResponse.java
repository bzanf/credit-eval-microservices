package io.github.bzanf.creditevaluatorms.application.responses;

import io.github.bzanf.creditevaluatorms.domain.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class CreditEvaluatorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Card> cards;
}
