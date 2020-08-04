package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Override
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @Override
    public Email getEmail(long id) {
        Optional<Email> result = emailRepository.findById(id);

        Email email;

        if (result.isPresent()) {
            email = result.get();
        } else {
            // we didn't find the email
            throw new RuntimeException("Did not find email id - " + id);
        }

        return email;
    }

    @Override
    public void addEmail(Email email) {

        emailRepository.save(email);
    }


}
