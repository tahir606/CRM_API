package com.example.CRM.LeadStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead,Integer> {
}
