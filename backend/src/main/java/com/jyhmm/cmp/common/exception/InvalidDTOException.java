package com.jyhmm.cmp.common.exception;

import java.io.Serial;

public class InvalidDTOException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3457037028902539680L;

    public InvalidDTOException() {
        super();
    }

    public InvalidDTOException(String message) {
        super(message);
    }

    public InvalidDTOException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDTOException(Throwable cause) {
        super(cause);
    }
}
