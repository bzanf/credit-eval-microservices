package io.github.bzanf.creditevaluatorms.application.responses;

import io.github.bzanf.creditevaluatorms.domain.entities.Card;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class CardResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String network;
    private BigDecimal creditLimit;

    public Card toModel() {
        return new Card(name, network, creditLimit);
    }
}
