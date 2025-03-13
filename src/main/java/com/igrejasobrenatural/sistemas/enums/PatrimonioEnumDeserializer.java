package com.igrejasobrenatural.sistemas.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PatrimonioEnumDeserializer extends JsonDeserializer<Set<PatrimonioEnum>> {

    @Override
    public Set<PatrimonioEnum> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Set<PatrimonioEnum> enums = new HashSet<>();
        String[] values = p.getText().split(",");
        for (String value : values) {
            enums.add(PatrimonioEnum.valueOf(value.trim().toUpperCase()));
        }
        return enums;
    }
}
