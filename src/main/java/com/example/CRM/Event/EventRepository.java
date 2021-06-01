package com.example.CRM.Event;

import com.example.CRM.Note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findAllByClientID(int eventId);
    List<Event> findAllByLeadsId(int leadId);
}
