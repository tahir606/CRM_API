package com.example.CRM.User;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
    @Component
public class UsersModelAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>>{
    @Override
    public EntityModel<Users> toModel(Users users) {

        return EntityModel.of(users, //
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).one( users.getUserCode())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
