package com.example.CRM.Rights;

import com.example.CRM.User.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RightsListModelAssembler implements RepresentationModelAssembler<RightsList , EntityModel<RightsList>> {
    @Override
    public EntityModel<RightsList> toModel(RightsList entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(RightsListController.class).one( entity.getRightsCode())).withSelfRel(),
                linkTo(methodOn(RightsListController.class).all()).withRel("rights_list"));
    }
}
