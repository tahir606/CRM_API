package com.example.CRM.Product;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModuleModelAssembler implements RepresentationModelAssembler<ProductModule , EntityModel<ProductModule>> {
    @Override
    public EntityModel<ProductModule> toModel(ProductModule entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(ProductModuleController.class).one( entity.getPmID())).withSelfRel(),
                linkTo(methodOn(ProductModuleController.class).all()).withRel("product_module"));
    }
}
