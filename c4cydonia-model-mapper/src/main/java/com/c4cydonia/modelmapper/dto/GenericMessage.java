/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import com.c4cydonia.modelmapper.validation.NullOrNotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getters, setters, toString, what else?
@Builder //TBD
@NoArgsConstructor //Basic constructor without args
@AllArgsConstructor //All args constructor
public class GenericMessage {
    @NotNull
    private String title;

    @NotNull
    private String messageDataType;

    @NotBlank
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]{2}:){2}[0-9]{2}[+|-][0-9]{2}:[0-9]{2}",
            message = "Date format: ISO-8601 with Offset")
    private String createdAt;

    private String executionTime;

    @NotNull
    private String isActive;

    @NullOrNotBlank(message = "Null or not blank")
    private Set<@NotBlank String> senders;
    private Map<String, Object> data = new HashMap();
}
