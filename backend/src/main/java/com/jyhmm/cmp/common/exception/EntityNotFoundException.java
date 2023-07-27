package com.jyhmm.cmp.common.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5131200153205704975L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
