package com.example.CRM.Phone;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneListRepository extends JpaRepository<PhoneList,Integer> {
    List<PhoneList> findAllByClientID(int id);
}
