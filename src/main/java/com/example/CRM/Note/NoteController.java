package com.example.CRM.Note;

import com.example.CRM.User.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/note_store")
public class NoteController {
    private final NoteRepository noteRepository;
    private final NoteModelAssembler noteModelAssembler;

    public NoteController(NoteRepository noteRepository, NoteModelAssembler noteModelAssembler) {
        this.noteRepository = noteRepository;
        this.noteModelAssembler = noteModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Note> one(@PathVariable int id) {

        Note note = noteRepository.findById(id)//
                .orElseThrow(() -> new NoteNotFoundException(id));

        return noteModelAssembler.toModel(note);
    }
    @GetMapping
    CollectionModel<EntityModel<Note>> all() {

        List<EntityModel<Note>> note = noteRepository.findAll().stream() //
                .map(noteModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(note, linkTo(methodOn(NoteController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addNotes(@RequestBody Note note) {

        EntityModel<Note> entityModel = noteModelAssembler.toModel(noteRepository.save(note));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateNotes(@RequestBody Note note, @PathVariable int id){
        Note notesUpdate = noteRepository.findById(id)//
            .map(notes ->{
                notes.setClientID(note.getClientID());
                notes.setContactID(note.getContactID());
                notes.setCreatedBy(note.getCreatedBy());
                notes.setCreatedOn(note.getCreatedOn());
                notes.setLeadsId(note.getLeadsId());
                notes.setnID(note.getnID());
                notes.setPsID(note.getPsID());
                notes.setText(note.getText());
                return noteRepository.save(notes);
            })//
        .orElseGet(()->{
           note.setNoteCode(id);
           return noteRepository.save(note);
        });
        EntityModel<Note> noteEntityModel = noteModelAssembler.toModel(notesUpdate);
        return ResponseEntity//
            .created(noteEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(noteEntityModel);
    }
}
