package io.github.bzanf.cardmicroservice.application.commands;

import io.github.bzanf.cardmicroservice.domain.entities.Card;
import io.github.bzanf.cardmicroservice.domain.enums.CardNetwork;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SaveCard implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private CardNetwork network;
    private BigDecimal income;
    private BigDecimal creditLimit;

    public Card toModel() {
        return new Card(name, network, income, creditLimit);
    }
}
