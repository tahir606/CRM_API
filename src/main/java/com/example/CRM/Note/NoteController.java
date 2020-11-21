package com.example.CRM.Note;

import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteRepository noteRepository;
    private final NoteModelAssembler noteModelAssembler;
    private final EmailDBHandler emailDBHandler;

    public NoteController(NoteRepository noteRepository, NoteModelAssembler noteModelAssembler, EmailDBHandler emailDBHandler) {
        this.noteRepository = noteRepository;
        this.noteModelAssembler = noteModelAssembler;
        this.emailDBHandler = emailDBHandler;
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

    @RequestMapping("/view")
    public CollectionModel<EntityModel<Note>> getNotes() {

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

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @RequestMapping("/addNote")
    public boolean addNote(@RequestBody Note note) {
        note.setCreatedOn(timestamp);
        emailDBHandler.insertNotes(note);
        return true;
    }

    @RequestMapping("/updateNote")
    public boolean updateNote(@RequestParam int noteCode, @RequestParam String noteText) {
        Note notesUpdate = emailDBHandler.findNote(noteCode);

        notesUpdate.setText(noteText);
        notesUpdate.setCreatedOn(timestamp);
        emailDBHandler.insertNotes(notesUpdate);
        return true;
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateNotes(@RequestBody Note note, @PathVariable int id) {
        Note notesUpdate = noteRepository.findById(id)//
                .map(notes -> {
                    notes.setClientID(note.getClientID());
                    notes.setContactID(note.getContactID());
                    notes.setCreatedBy(note.getCreatedBy());
                    notes.setCreatedOn(note.getCreatedOn());
                    notes.setLeadsId(note.getLeadsId());
                    notes.setEmailId(note.getEmailId());
                    notes.setPsID(note.getPsID());
                    notes.setText(note.getText());
                    return noteRepository.save(notes);
                })//
                .orElseGet(() -> {
                    note.setNoteCode(id);
                    return noteRepository.save(note);
                });
        EntityModel<Note> noteEntityModel = noteModelAssembler.toModel(notesUpdate);
        return ResponseEntity//
                .created(noteEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(noteEntityModel);
    }

    @RequestMapping("/deleteNote/{noteId}")
    public boolean deleteNote(@PathVariable int noteId) {
        Note deleteNote = emailDBHandler.findNote(noteId);

        deleteNote.setFreeze(1);
        emailDBHandler.insertNotes(deleteNote);

        return true;
    }

}
