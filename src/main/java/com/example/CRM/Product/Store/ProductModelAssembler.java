package com.example.CRM.Product.Store;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product , EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(ProductController.class).one( entity.getPsID())).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("product_store"));
    }
}
