package com.btg_pactual.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnakeCaseToCamelCaseDeserializer extends JsonDeserializer<Map<String, Object>> {

    @Override
    public Map<String, Object> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode rootNode = mapper.readTree(p);

        return convertSnakeToCamel(rootNode);
    }
    
    private Map<String, Object> convertSnakeToCamel(JsonNode node) {
        Map<String, Object> result = new HashMap<>();

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String camelCaseKey = toCamelCase(field.getKey());
                result.put(camelCaseKey, convertSnakeToCamel(field.getValue()));
            }
        } else if (node.isArray()) {
            result = new HashMap<>();
            for (JsonNode element : node) {
                result.putAll(convertSnakeToCamel(element));
            }
        } else {
            result.put(node.asText(), node.asText());
        }

        return result;
    }

    private String toCamelCase(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        boolean nextIsUpperCase = false;
        for (char c : snakeCase.toCharArray()) {
            if (c == '_') {
                nextIsUpperCase = true;
            } else {
                if (nextIsUpperCase) {
                    camelCase.append(Character.toUpperCase(c));
                    nextIsUpperCase = false;
                } else {
                    camelCase.append(c);
                }
            }
        }
        return camelCase.toString();
    }
}
