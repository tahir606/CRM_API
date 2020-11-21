package com.example.CRM.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select u.fname from users u where u.ucode= :userCode",nativeQuery = true)
    String findByFullName(int userCode);
}
