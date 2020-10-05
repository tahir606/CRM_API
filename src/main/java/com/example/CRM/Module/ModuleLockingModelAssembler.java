package com.example.CRM.Module;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ModuleLockingModelAssembler implements RepresentationModelAssembler<ModuleLocking, EntityModel<ModuleLocking>> {
    @Override
    public EntityModel<ModuleLocking> toModel(ModuleLocking entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(ModuleController.class).one( entity.getModuleId())).withSelfRel(),
                linkTo(methodOn(ModuleController.class).all()).withRel("module_locking"));
    }
}
