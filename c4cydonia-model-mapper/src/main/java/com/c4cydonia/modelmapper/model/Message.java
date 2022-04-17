/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getters, setters, toString, what else?
@Builder //TBD
@NoArgsConstructor //Basic constructor without args
@AllArgsConstructor //All args constructor
public class Message {
    private String title;
    private String messageDataType;
    private LocalDateTime createdAt;
    private LocalDateTime executionTime;
    private Boolean isActive;
    private Set<String> senders;
    private Map<String, Object> data = new HashMap();
}
