package com.example.CRM.Note;

import com.example.CRM.JCode.EmailDBHandler;
import com.example.CRM.Phone.PhoneList;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
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
    @RequestMapping("/getNotesByEmailId/{emailId}")
    public CollectionModel<EntityModel<Note>> getNotesByEmailId(@PathVariable int emailId) {

        List<EntityModel<Note>> note = emailDBHandler.findNoteByEmailId(emailId).stream() //
                .map(noteModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(NoteController.class).all()).withSelfRel());
    }
    @RequestMapping("/getNotesByClientId/{clientId}")
    public CollectionModel<EntityModel<Note>> getNotesByClientId(@PathVariable int clientId) {

        List<EntityModel<Note>> note = emailDBHandler.findNoteByClientId(clientId).stream() //
                .map(noteModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(NoteController.class).all()).withSelfRel());
    }
    @RequestMapping("/getNotesByLeadId/{leadsId}")
    public CollectionModel<EntityModel<Note>> getNotesByLeadId(@PathVariable int leadsId) {

        List<EntityModel<Note>> note = emailDBHandler.findNoteByLeadId(leadsId).stream() //
                .map(noteModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(NoteController.class).all()).withSelfRel());
    }
    @RequestMapping("/getNotesByProductId/{productId}")
    public CollectionModel<EntityModel<Note>> getNotesByProductId(@PathVariable int productId) {

        List<EntityModel<Note>> note = emailDBHandler.findNoteByProductId(productId).stream() //
                .map(noteModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(NoteController.class).all()).withSelfRel());
    }
    @RequestMapping("/getNotesByContactId/{contactId}")
    public CollectionModel<EntityModel<Note>> getNotesByContactId(@PathVariable int contactId) {

        List<EntityModel<Note>> note = emailDBHandler.findNoteByContactId(contactId).stream() //
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



    @RequestMapping("/addNote")
    public boolean addNote(@RequestBody Note note) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        note.setCreatedOn(timestamp);
       emailDBHandler.insertNotes(note);
        return true;
    }

    @RequestMapping("/updateNote")
    public boolean updateNote(@RequestParam int noteCode, @RequestParam String noteText) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Note notesUpdate = emailDBHandler.findNote(noteCode);

        notesUpdate.setText(noteText);
        notesUpdate.setCreatedOn(timestamp);
        emailDBHandler.insertNotes(notesUpdate);
        return true;
    }
    @RequestMapping("/updateNoteLeadList")
    ResponseEntity<?> updateNoteLeadList(@RequestBody Note note) {

        int isUpdate= emailDBHandler.updateNoteLeadList(note);
        if (isUpdate==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(note.getNoteCode(),HttpStatus.OK);
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

        emailDBHandler.deleteNote(deleteNote);

        return true;
    }

}
