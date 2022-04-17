/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.model.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/messages/jackson")
@Validated
@RequiredArgsConstructor
public class MessageControllerJackson {

    @PostMapping(path ="/messageDto", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void processJsonMessageDto(@RequestBody @Valid GenericMessage genericMessage) {
        System.out.println("MessageDto received: " + genericMessage);
    }

    @PostMapping(path ="/message", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void processJsonMessage(@RequestBody @Valid Message message) {
        System.out.println("Message received: " + message);
    }
}
