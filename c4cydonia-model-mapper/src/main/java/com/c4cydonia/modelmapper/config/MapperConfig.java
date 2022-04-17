/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.c4cydonia.modelmapper.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c4cydonia.modelmapper.dto.GenericMessage;
import com.c4cydonia.modelmapper.model.Message;

import static com.c4cydonia.modelmapper.config.MapperCatalogue.MESSAGE_CONVERTER;

@Configuration
public class MapperConfig {

    @Bean(name = "modelMapperV1")
    public ModelMapper modelMapperV1() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Bean(name = "modelMapperV2")
    public ModelMapper modelMapperV2() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());

        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                //.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        // .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(GenericMessage.class, Message.class).addMappings(MESSAGE_CONVERTER);
        return modelMapper;
    }
}
