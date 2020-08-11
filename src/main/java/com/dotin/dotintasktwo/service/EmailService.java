package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {

    List<Email> getAllEmails();

    Email getEmail(long id);

    void addEmail(Email email);

    List<Email> getInbox(Employee receiver);
    List<Email> getOutbox(Employee sender);

    List<Email> findAll(Pageable pageable);
    List<Email> findAll();


}


