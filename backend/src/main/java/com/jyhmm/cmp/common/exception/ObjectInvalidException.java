package com.jyhmm.cmp.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ObjectInvalidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1123084288233364636L;

    private final List<String> errorMessages;
}
