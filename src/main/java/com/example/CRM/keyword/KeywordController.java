package com.example.CRM.keyword;


import com.example.CRM.User.Users;
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
@RequestMapping("/keyword")
public class KeywordController {
    private final KeywordModelAssembler keywordModelAssembler;
    private final KeywordRepository keywordRepository;

    public KeywordController(KeywordModelAssembler keywordModelAssembler, KeywordRepository keywordRepository) {
        this.keywordModelAssembler = keywordModelAssembler;
        this.keywordRepository = keywordRepository;
    }

    @GetMapping("/{id}")
    EntityModel<Keyword> one(@PathVariable int id) {
        Keyword keyword = keywordRepository.findById(id)//
                .orElseThrow(() -> new KeywordNotFoundException(id));
        return keywordModelAssembler.toModel(keyword);
    }

    @GetMapping
    CollectionModel<EntityModel<Keyword>> all() {

        List<EntityModel<Keyword>> keyword = keywordRepository.findAll().stream() //
                .map(keywordModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(keyword, linkTo(methodOn(KeywordController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newKeyword(@RequestBody Keyword keyword) {
        EntityModel<Keyword> entityModel = keywordModelAssembler.toModel(keywordRepository.save(keyword));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestMapping("/addKeywords")
    public void addKeyword(@RequestBody List<Keyword> keywordList) {
        for (Keyword keyword : keywordList) {
            keywordRepository.save(keyword);
        }
    }

    @RequestMapping("/getKeywords")
    public CollectionModel<EntityModel<Keyword>> getKeywords() {

        List<EntityModel<Keyword>> keyword = keywordRepository.findAll().stream() //
                .map(keywordModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(keyword, linkTo(methodOn(KeywordController.class).all()).withSelfRel());
    }

    @RequestMapping("/removeKeyword/{code}")
    public void deleteKeyword(@PathVariable int code) {
        keywordRepository.deleteById(code);
    }

}
