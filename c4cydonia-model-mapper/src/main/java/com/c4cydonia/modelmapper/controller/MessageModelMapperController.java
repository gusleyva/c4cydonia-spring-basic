/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/v1/messages/modelmapper")
@Validated
public class MessageModelMapperController {
    private final ModelMapper modelMapperV1;
    private final ModelMapper modelMapperV2;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageModelMapperController(ModelMapper modelMapperV1, ModelMapper modelMapperV2, ObjectMapper objectMapper) {
        this.modelMapperV1 = modelMapperV1;
        this.modelMapperV2 = modelMapperV2;
        this.objectMapper = objectMapper;
    }

    //TODO - Review
    @PostMapping("/v1/string")
    public void processGenericMessageMapperV1String(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v1: " + input);
        var genericMessage = modelMapperV1.map(input, GenericMessage.class);
        System.out.println("GenericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/v1/tojsonnode")
    public void processGenericMessageMapperV1ToJsonNode(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v1: " + input);
        JsonNode data = objectMapper.readTree(input);
        var message = modelMapperV1.map(data, GenericMessage.class);
        System.out.println("Message data with mapper: " + message);
    }

    @PostMapping("/v1/jsonnode")
    public void processGenericMessageMapperV1Json(@RequestBody JsonNode input) throws JsonProcessingException {
        System.out.println("Message received v1: " + input);
        // TODO
        var message = modelMapperV1.map(input, GenericMessage.class);
        System.out.println("Message data with mapper: " + message);
    }


    @PostMapping("/v2/string")
    public void processGenericMessageMapperV2String(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v2: " + input);
        JsonNode data = objectMapper.readTree(input);
        var genericMessage = modelMapperV2.map(data, GenericMessage.class);
        // GenericMessage.setData(data);
        System.out.println("GenericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/v2/json")
    public void processGenericMessageMapperV2Json(@RequestBody JsonNode input) throws JsonProcessingException {
        System.out.println("Message received v2: " + input);
        var genericMessage = modelMapperV2.map(input, GenericMessage.class);
        // genericMessage.setData(data);
        System.out.println("genericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/v2/tomap")
    public void processGenericMessageMapperV2ToMap(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v2: " + input);
        var data = objectMapper.readValue(input, Map.class);
        var genericMessage = modelMapperV2.map(data, GenericMessage.class);
        // genericMessage.setData(data);
        System.out.println("genericMessage data with mapper: " + genericMessage);
    }

    @PostMapping("/v2/message")
    public void processGenericMessageToMessage(@RequestBody String input) throws JsonProcessingException {
        System.out.println("Message received v2: " + input);
        var data = objectMapper.readValue(input, Map.class);
        var genericMessage = modelMapperV2.map(data, GenericMessage.class);
        // genericMessage.setData(data);
        System.out.println("genericMessage data with mapper: " + genericMessage);
        var message = modelMapperV2.map(genericMessage, Message.class);
        System.out.println("Message data: " + message);
    }

}
