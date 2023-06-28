package com.jyhmm.cmp.common.exception;

import java.io.Serial;

public class InvalidEntityException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3457037028902539680L;

    public InvalidEntityException() {
        super();
    }

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(Throwable cause) {
        super(cause);
    }
}
