package com.example.CRM.Email.Setiings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SettingRepository extends JpaRepository<EmailSettings, Integer> {
    @Modifying
    @Query("update email_settings seting set seting.replacementKeyword = :keyword")
    void setReplacementKeywordForEmailSettings(@Param("keyword") String keyword);
}
