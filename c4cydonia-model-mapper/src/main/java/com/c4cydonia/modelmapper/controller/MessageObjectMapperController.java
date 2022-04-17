/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.model.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/messages/objectmapper")
@Validated
@RequiredArgsConstructor
public class MessageObjectMapperController {
    private final ObjectMapper objectMapper;

    @PostMapping("/string")
    public void processGenericMessageObjectMapperString(@RequestBody String input) {
        System.out.println("Input received string: " + input);
        var genericMessage = objectMapper.convertValue(input, GenericMessage.class);
        System.out.println("genericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/json")
    public void processGenericMessageObjectMapperJson(@RequestBody JsonNode input) {
        System.out.println("Input received JSON: " + input);
        var genericMessage = objectMapper.convertValue(input, GenericMessage.class);
        System.out.println("genericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/message")
    public void processMessageObjectMapperJson(@RequestBody JsonNode input) {
        System.out.println("Input received JSON: " + input);
        var message = objectMapper.convertValue(input, Message.class);
        System.out.println("Message data with mapper: " + message);
    }
}
