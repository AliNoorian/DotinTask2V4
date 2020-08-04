package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {

    List<Email> getAllEmails();

    Email getEmail(long id);

    void addEmail(Email email);

}


