package io.github.bzanf.creditevaluatorms.application.exceptions;

import java.io.Serial;

public class DataWithInvalidFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DataWithInvalidFormatException(String msg) {
        super(msg);
    }
}
