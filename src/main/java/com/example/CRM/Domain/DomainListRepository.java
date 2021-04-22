package com.example.CRM.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DomainListRepository extends JpaRepository<Domain, Integer> {

//    @Query(value = "select d.name from domain_list d where d.name =:1",nativeQuery = true)
    List<Domain> findNameByWhiteBlackList(int domain);

    List<Domain> findAllByWhiteBlackList(int id);
}
