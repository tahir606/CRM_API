package com.example.CRM.Email.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<TicketHistory, Integer> {
List<TicketHistory> findByEmailId(int emailId);
        }
