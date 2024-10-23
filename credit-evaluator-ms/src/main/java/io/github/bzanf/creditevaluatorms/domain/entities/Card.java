package io.github.bzanf.creditevaluatorms.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Card implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String network;
    private BigDecimal creditLimit;
}
