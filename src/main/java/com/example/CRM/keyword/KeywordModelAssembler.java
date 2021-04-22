package com.example.CRM.keyword;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class KeywordModelAssembler implements RepresentationModelAssembler<Keyword, EntityModel<Keyword>> {
    @Override
    public EntityModel<Keyword> toModel(Keyword keyword) {
        return EntityModel.of(keyword, //
                WebMvcLinkBuilder.linkTo(methodOn(KeywordController.class).one( keyword.getCode())).withSelfRel(),
                linkTo(methodOn(KeywordController.class).all()).withRel("keyword"));
    }
}
