package com.example.CRM.Source;

import com.example.CRM.User.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/source")
public class SourceController {
    private final SourceRepository sourceRepository;
    private final SourceModelAssembler sourceModelAssembler;

    public SourceController(SourceRepository sourceRepository, SourceModelAssembler sourceModelAssembler) {
        this.sourceRepository = sourceRepository;
        this.sourceModelAssembler = sourceModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Source> one(@PathVariable int id) {

        Source source = sourceRepository.findById(id)//
                .orElseThrow(() -> new SourceNotFoundException(id));

        return sourceModelAssembler.toModel(source);
    }

    @GetMapping
    CollectionModel<EntityModel<Source>> all() {

        List<EntityModel<Source>> source = sourceRepository.findAll().stream() //
                .map(sourceModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(source, linkTo(methodOn(SourceController.class).all()).withSelfRel());
    }

    @RequestMapping("/getAllSource")
    CollectionModel<EntityModel<Source>> getAllResources() {
        List<EntityModel<Source>> source = sourceRepository.findAll().stream() //
                .map(sourceModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(source, linkTo(methodOn(SourceController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addSource(@RequestBody Source source) {

        EntityModel<Source> entityModel = sourceModelAssembler.toModel(sourceRepository.save(source));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateSource(@RequestBody Source source, @PathVariable int id) {
        Source sourceUpdate = sourceRepository.findById(id)//
                .map(sources -> {
                    sources.setDescription(source.getDescription());
                    sources.setName(source.getName());
                    return sourceRepository.save(sources);
                })//
                .orElseGet(() -> {
                    source.setSourceID(id);
                    return sourceRepository.save(source);
                });
        EntityModel<Source> sourceEntityModel = sourceModelAssembler.toModel(sourceUpdate);
        return ResponseEntity//
                .created(sourceEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(sourceEntityModel);
    }
}
