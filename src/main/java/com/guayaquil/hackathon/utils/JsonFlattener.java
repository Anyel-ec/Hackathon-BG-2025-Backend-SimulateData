package com.guayaquil.hackathon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class JsonFlattener {

    private static final Set<String> EXCLUDE_FIELDS = Set.of("hometown_id", "location_id");

    public static Map<String, Object> flattenJson(JsonNode node, String prefix) {
        Map<String, Object> flatMap = new LinkedHashMap<>();

        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String key = prefix + entry.getKey();
                if (!EXCLUDE_FIELDS.contains(key)) {
                    flatMap.putAll(flattenJson(entry.getValue(), key + "_"));
                }
            });
        } else if (node.isArray()) {
            List<String> values = new ArrayList<>();
            node.forEach(element -> {
                if (element.isObject()) {
                    if (element.has("message")) {
                        values.add(element.get("message").asText()); // Para `posts`, solo guardar `message`
                    } else if (element.has("name")) {
                        values.add(element.get("name").asText()); // Para `likes`, `brands`, `photos`, etc.
                    } else if (element.has("position") && element.has("employer")) {
                        values.add(element.get("position").asText() + " en " + element.get("employer").get("name").asText()); // Para `work`
                    } else if (element.has("degree") && element.has("school")) {
                        values.add(element.get("degree").asText() + " en " + element.get("school").get("name").asText()); // Para `education`
                    }
                } else {
                    values.add(element.asText());
                }
            });
            flatMap.put(prefix.substring(0, prefix.length() - 1), values);
        } else {
            flatMap.put(prefix.substring(0, prefix.length() - 1), node.asText());
        }
        return flatMap;
    }

    public static String flattenFacebookUser(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        Map<String, Object> flatJson = flattenJson(rootNode, "");
        return objectMapper.writeValueAsString(flatJson);
    }
}
