package com.example.CRM.Note;

import com.example.CRM.User.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NoteModelAssembler implements RepresentationModelAssembler<Note, EntityModel<Note>> {
    @Override
    public EntityModel<Note> toModel(Note entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(NoteController.class).one( entity.getNoteCode())).withSelfRel(),
                linkTo(methodOn(NoteController.class).all()).withRel("note_store"));
    }
}
