package io.github.bzanf.creditevaluatorms.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCard implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Client client;
    private List<Card> cards;
}
