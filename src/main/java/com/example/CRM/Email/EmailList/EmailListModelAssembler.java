package com.example.CRM.Email.EmailList;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class EmailListModelAssembler implements RepresentationModelAssembler<EmailList , EntityModel<EmailList>> {
    @Override
    public EntityModel<EmailList> toModel(EmailList entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(EmailListController.class).one(entity.getEmail_ID())).withSelfRel(),
                linkTo(methodOn(EmailListController.class).all()).withRel("email_list"));
    }
}
