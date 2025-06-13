package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.mapper.DefinitionRestMapper;
import com.example.demo.endpoint.rest.model.DefinitionResponse;
import com.example.demo.service.definition.GenerateDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hazavao")
@RequiredArgsConstructor
public class HazavaoController {

    private final GenerateDefinitionService generateDefinitionService;

    @GetMapping
    public Mono<DefinitionResponse> hazavao(@RequestParam String teny) {
        return generateDefinitionService.generateDefinition(teny)
                .map(DefinitionRestMapper::toResponse);
    }
}
