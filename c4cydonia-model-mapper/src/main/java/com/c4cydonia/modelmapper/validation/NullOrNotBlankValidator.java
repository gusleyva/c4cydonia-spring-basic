/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.validation;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, Set<String>> {

    public void initialize(NullOrNotBlank parameters) {
        // Nothing to do here
    }

    public boolean isValid(Set<String> value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.isEmpty();
    }
}
