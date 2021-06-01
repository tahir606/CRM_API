package com.example.CRM.Task;

import com.example.CRM.User.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler  implements RepresentationModelAssembler<Task , EntityModel<Task>> {
    @Override
    public EntityModel<Task> toModel(Task entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(TaskController.class).one(entity.getTaskID())).withSelfRel(),
                linkTo(methodOn(TaskController.class).all()).withRel("task_store"));
    }
}
