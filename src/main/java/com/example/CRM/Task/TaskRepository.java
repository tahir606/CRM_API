package com.example.CRM.Task;

import com.example.CRM.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task ,Integer> {
    List<Task> findAllByClientID(int taskId);
    List<Task> findAllByLeadsId(int leadId);
    List<Task> findAllByPsID(int productId);
    List<Task> findAllByStatus(int taskId);
}
