/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.applicationproperties.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class GenericValidator {

    @Getter
    @Setter
    @Value("${entity.welcome}")
    private String welcome;

    @Getter
    @Setter
    @Value("#{${entity.max-size-long}}")
    private Map<String, Long> maxSizeLong;

    @Getter
    @Setter
    @Value("#{${entity.allowed-formats}}")
    private Map<String, List<String>> allowedFormats;

    @Getter
    @Setter
    @Value("#{${entity.restricted-formats}}")
    private Map<String, List<String>> restrictedFormats;
}
