package com.igorac.algamoney.api.resource.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResourceUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static URI resourceToURI(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{codigo}")
            .buildAndExpand(id)
            .toUri();
    }

    public static <T> T applyPatch(JsonPatch patch, T target, Class<T> targetClass) throws JsonPatchException {
        JsonNode jsonTarget = objectMapper.convertValue(target, JsonNode.class);
        JsonNode patched = patch.apply(jsonTarget);
        return objectMapper.convertValue(patched, targetClass);
    }
}
