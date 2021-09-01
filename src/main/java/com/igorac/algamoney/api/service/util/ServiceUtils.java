package com.igorac.algamoney.api.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public class ServiceUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T applyPatch(JsonPatch patch, T target, Class<T> targetClass) throws JsonPatchException {
        JsonNode jsonTarget = objectMapper.convertValue(target, JsonNode.class);
        JsonNode patched = patch.apply(jsonTarget);
        return objectMapper.convertValue(patched, targetClass);
    }
}
