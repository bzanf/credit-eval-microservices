package io.github.bzanf.cardmicroservice.application.responses;

import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String network;
    private BigDecimal creditLimit;

    public static CardsByClientResponse fromModel(ClientCard model) {
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getNetwork().toString(),
                model.getCreditLimit()
        );
    }
}
