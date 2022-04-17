/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.model.Message;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.nonNull;

public class MapperCatalogue {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static final Converter<String, LocalDateTime> LOCAL_DATE_TIME_CONVERTER = ctx -> nonNull(ctx.getSource())
            ? LocalDateTime.parse(ctx.getSource(), formatter) : null;

    public static final ExpressionMap<GenericMessage, Message>
            MESSAGE_CONVERTER = mapper -> {
        mapper.using(LOCAL_DATE_TIME_CONVERTER)
                .map(GenericMessage::getCreatedAt,
                        Message::setCreatedAt);
        mapper.map(src -> LocalDateTime.now(UTC), Message::setExecutionTime);
        // mapper.map(src -> new Object(), AutomationMessage::setObject);
        mapper.map(GenericMessage::getMessageDataType, Message::setMessageDataType);
        mapper.map(GenericMessage::getSenders, Message::setSenders);
    };

    private MapperCatalogue() {
    }

}
