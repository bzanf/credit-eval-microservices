package io.github.bzanf.clientmicroservice.application.commands;

import io.github.bzanf.clientmicroservice.domain.entities.Client;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SaveClient implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String idCode;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(idCode, name, age);
    }
}
