package com.example.CRM.Note;

import javax.persistence.Query;

import com.example.CRM.Phone.PhoneList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class NoteCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void deleteNote(int noteId){
        Query query=  entityManager.createQuery("delete from note_store where noteCode=:noteCode");
        query.setParameter("noteCode",noteId);
        query.executeUpdate();
    }

    public int updateNoteList(Note note){
        Query query = entityManager.createQuery("update note_store set clientID=:clientId where noteCode=:noteId");
        query.setParameter("noteId", note.getNoteCode());
        query.setParameter("clientId", note.getClientID());

        return query.executeUpdate();
    }
}
