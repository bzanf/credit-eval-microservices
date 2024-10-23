package io.github.bzanf.creditevaluatorms.application.exceptions;

import java.io.Serial;

public class MicroserviceCommunicationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public MicroserviceCommunicationException(String msg) {
        super(msg);
    }
}
