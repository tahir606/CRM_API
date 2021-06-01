package com.example.CRM.Note;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByEmailId(int emailId);

    List<Note> findAllByClientID(int clientId);
    List<Note> findAllByContactID(int contactId);
    List<Note> findAllByLeadsId(int leadsId);
    List<Note> findAllByPsID(int productId);
}
