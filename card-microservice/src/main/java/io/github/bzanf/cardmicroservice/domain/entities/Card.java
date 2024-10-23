package io.github.bzanf.cardmicroservice.domain.entities;

import io.github.bzanf.cardmicroservice.domain.enums.CardNetwork;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CardNetwork network;
    private BigDecimal income;
    private BigDecimal creditLimit;

    public Card(String name, CardNetwork network, BigDecimal income, BigDecimal creditLimit) {
        this.name = name;
        this.network = network;
        this.income = income;
        this.creditLimit = creditLimit;
    }

}
