package com.example.demo.endpoint.rest.mapper;

import com.example.demo.domain.model.Definition;
import com.example.demo.endpoint.rest.model.DefinitionResponse;

public interface DefinitionRestMapper {
    static DefinitionResponse toResponse(Definition definition) {
        return new DefinitionResponse(definition.getDefinition());
    }
}
