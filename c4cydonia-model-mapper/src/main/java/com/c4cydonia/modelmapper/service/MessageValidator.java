/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.c4cydonia.modelmapper.dto.GenericMessage;

/**
 *
 */
@Service
@Validated
public class MessageValidator {

    public void validateMessageDto(@Valid GenericMessage messageDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GenericMessage>> violations =
                validator.validate(messageDto);
        System.out.println("violations: " + violations);
    }
}
