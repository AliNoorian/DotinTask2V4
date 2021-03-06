package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@Service
public interface EmailService {

    List<Email> getAllEmails();

    Email getEmail(long id);

    void addEmail(Email email) throws SQLException;

    List<Email> getInbox(Employee receiver, Pageable pageable);
    List<Email> getInbox(Employee receiver);
    List<Email> getSent(Employee sender, Pageable pageable);
    List<Email> getSent(Employee sender);
    List<Email> findAll(Pageable pageable);

    List<Email> findAll();

    void deleteEmail(long theId);


    //Email getFile(String fileId);

}


