package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE e.sender =:sender")
    Page<Email> getEmailBySenderEquals(@Param("sender") Employee sender, Pageable pageable);

    @Query("SELECT e FROM Email e WHERE e.receivers =:receiver")
    Page<Email> getEmailByReceiversEquals(@Param("receiver") Employee receiver, Pageable pageable);



    List<Email> findAllBySenderEquals(Employee sender, Pageable pageable);
    List<Email> findAllBySenderEquals(Employee sender);
    List<Email> findAllByReceiversEquals(Employee receiver, Pageable pageable);
    List<Email> findAllByReceiversEquals(Employee receiver);


}
