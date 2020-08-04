package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {


}
