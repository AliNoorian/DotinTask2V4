package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
    @Transactional
    public void addEmail(Email email) {

        emailRepository.save(email);
    }

    @Override
    public Page<Email> getInbox(Employee receiver, Pageable pageable) {
        return emailRepository.getEmailByReceiversEquals(receiver, pageable);
    }

    @Override
    public Page<Email> getSent(Employee sender, Pageable pageable) {
        return emailRepository.getEmailBySenderEquals(sender, pageable);
    }


    @Override
    public List<Email> findAll(Pageable pageable) {
        int pageNo = pageable.getPageNumber();
        return emailRepository.findAll(PageRequest.of(pageNo, 4, Sort.by("id").descending())).getContent();

    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    @Transactional
    public void storeFile(MultipartFile file, long emailId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            //   if(fileName.contains("..")) {
            //       throw new ("Sorry! Filename contains invalid path sequence " + fileName);
            //   }

            Email email = emailRepository.getOne(emailId);
            email.setFileName(fileName);
            email.setFileType(file.getContentType());
            email.setAttachment(file.getBytes());

        } catch (IOException ex) {
            //     throw new ("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

//    @Override
//    public Email getFile(String fileId) {
//        return emailRepository.findById(fileId)
//         //       .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }

}
