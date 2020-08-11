package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE e.sender =:sender")
    List<Email> getEmailBySenderEquals(Employee sender);

    @Query("SELECT e FROM Email e WHERE e.receivers =:receiver")
    List<Email> getEmailByReceiversEquals(Employee receiver);


}
