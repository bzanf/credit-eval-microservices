package io.github.bzanf.cardmicroservice.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class ClientCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idCode;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    private BigDecimal creditLimit;

}
