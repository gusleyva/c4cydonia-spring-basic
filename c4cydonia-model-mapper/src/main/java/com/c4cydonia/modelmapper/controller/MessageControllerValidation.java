/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.controller;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.service.MessageValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/message/validation")
@Validated
@RequiredArgsConstructor
public class MessageControllerValidation {
    private final ObjectMapper objectMapper;
    private final MessageValidator messageValidator;

    @PostMapping("/manual")
    public void processMessageMapperConstraints(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Manual constraints - Input received string: " + input);
        JsonNode data = objectMapper.readTree(input);
        var messageDto = objectMapper.convertValue(data, GenericMessage.class);
        var violations = getMessageDtoConstraints(messageDto);
        if (!violations.isEmpty()) {
            System.out.println("violations: " + violations);
        }
        System.out.println("Message data with mapper: " + messageDto);
    }

    private Set<ConstraintViolation<GenericMessage>> getMessageDtoConstraints(GenericMessage genericMessage) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GenericMessage>> violations =
                validator.validate(genericMessage);
        return violations;
    }

    @PostMapping("/v2/converttomodel1")
    public void processMessageMapper(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v3: " + input);
        // From string to messageDto using objectMapper?
        JsonNode data = objectMapper.readTree(input);
        var messageDto = getMessageDto(data);
        validateMessageDto(messageDto);
        System.out.println("Message data with mapper: " + messageDto);
        // TODO -> Once it is working, convert to model message
    }

    @Valid
    private GenericMessage getMessageDto(JsonNode data) {
        var messageDto = objectMapper.convertValue(data, GenericMessage.class);
        return messageDto;
    }

    private void validateMessageDto(@Valid GenericMessage genericMessage) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GenericMessage>> violations =
                validator.validate(genericMessage);
        System.out.println("violations: " + violations);
    }

    @PostMapping("/mapper/v5")
    public void processMessageMapperMessageService(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v3: " + input);
        JsonNode data = objectMapper.readTree(input);
        var messageDto = getMessageDto(data);
        // This should be a bean - Then it will work
        messageValidator.validateMessageDto(messageDto);
        System.out.println("Message data with mapper: " + messageDto);
    }
}
