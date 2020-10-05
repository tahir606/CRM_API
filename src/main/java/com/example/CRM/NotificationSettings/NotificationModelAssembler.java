package com.example.CRM.NotificationSettings;

import com.example.CRM.User.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NotificationModelAssembler implements RepresentationModelAssembler<Notification , EntityModel<Notification>> {
    @Override
    public EntityModel<Notification> toModel(Notification entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(NotificationController.class).one( entity.getNotificationId())).withSelfRel(),
                linkTo(methodOn(NotificationController.class).all()).withRel("notification_setting"));
    }
}
