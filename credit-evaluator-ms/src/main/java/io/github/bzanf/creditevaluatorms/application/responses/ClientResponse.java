package io.github.bzanf.creditevaluatorms.application.responses;

import io.github.bzanf.creditevaluatorms.domain.entities.Client;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ClientResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String idCode;
    private String name;
    private Integer age;

    public Client toModel() {
        return new Client(id, name, age);
    }
}
