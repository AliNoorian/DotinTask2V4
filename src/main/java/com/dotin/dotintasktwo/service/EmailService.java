package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    void storeFile(MultipartFile file, long emailId);

    //Email getFile(String fileId);

}


