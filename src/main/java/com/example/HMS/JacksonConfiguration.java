package com.example.HMS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    public JacksonConfiguration(ObjectMapper objectMapper){
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
