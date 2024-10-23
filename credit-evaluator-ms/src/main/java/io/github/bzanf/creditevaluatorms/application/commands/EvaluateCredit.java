package io.github.bzanf.creditevaluatorms.application.commands;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EvaluateCredit implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String idCode;
    private Long income;
}
