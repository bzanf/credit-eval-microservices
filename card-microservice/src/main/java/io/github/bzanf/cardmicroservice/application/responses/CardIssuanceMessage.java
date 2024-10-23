package io.github.bzanf.cardmicroservice.application.responses;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CardIssuanceMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cardId;
    private String clientIdCode;
    private String address;
    private BigDecimal creditLimit;
}
