package com.igorac.algamoney.api.resource.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResourceUtils {

    public static URI resourceToURI(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{codigo}")
            .buildAndExpand(id)
            .toUri();
    }

}
