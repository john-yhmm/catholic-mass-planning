package com.jyhmm.cmp.common.utils;

import com.jyhmm.cmp.common.exception.ObjectInvalidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new ObjectInvalidException(errorMessages);
        }
    }
}
