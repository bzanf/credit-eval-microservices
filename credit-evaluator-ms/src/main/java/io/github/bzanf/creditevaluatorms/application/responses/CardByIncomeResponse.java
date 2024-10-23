package io.github.bzanf.creditevaluatorms.application.responses;

import io.github.bzanf.creditevaluatorms.domain.entities.Card;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CardByIncomeResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private String network;
    private BigDecimal income;
    private BigDecimal creditLimit;

    public Card toModel() {
        return new Card(name, network, creditLimit);
    }
}
