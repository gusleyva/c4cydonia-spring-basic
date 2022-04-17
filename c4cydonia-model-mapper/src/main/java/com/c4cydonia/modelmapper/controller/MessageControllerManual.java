/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4cydonia.modelmapper.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/messages")
@Validated
@RequiredArgsConstructor
public class MessageControllerManual {
    private final ObjectMapper objectMapper;

    @PostMapping("/manual")
    public void processMessageOldSchool(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Input received: " + input);

        var payload = objectMapper.readValue(input, Map.class);
        // TODO -> Use messageDto, validate differences with snake case and so on
        var messageOldSchool = Message.builder()
                .messageDataType("TEST-MESSAGE")
                // .createdAt(payload.get("created_at"))
                .isActive(Boolean.getBoolean(payload.get("isActive").toString()))
                .senders(Set.of("REST-API"))
                .data(payload)
                .build();

        System.out.println("Message object mapped old school: " + messageOldSchool);
    }
}
