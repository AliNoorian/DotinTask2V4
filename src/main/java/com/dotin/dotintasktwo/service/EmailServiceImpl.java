package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.repository.EmailRepository;
import com.dotin.dotintasktwo.utility.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
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
            // we didn't find the email!
            throw new RuntimeException("ایمبل پیدا نشد " + id);
        }

        return email;
    }

    @Override
    @Transactional
    public void addEmail(Email email){
        email.setActive(true);
        email.setVersion(1);
        email.setCreateDate(new Time().getTime());

        emailRepository.save(email);
    }

    @Override
    public List<Email> getInbox(Employee receiver, Pageable pageable) {
        int pageNo = pageable.getPageNumber();
        return emailRepository.findAllByReceiversEquals(receiver,PageRequest.of(pageNo,4,Sort.by("id").ascending()));
    }

    @Override
    public List<Email> getInbox(Employee receiver) {
        return emailRepository.findAllByReceiversEquals(receiver);
    }

    @Override
    public List<Email> getSent(Employee sender, Pageable pageable) {
        int pageNo = pageable.getPageNumber();
        return emailRepository.findAllBySenderEquals(sender,PageRequest.of(pageNo,4,Sort.by("id").ascending()));
    }

    @Override
    public List<Email> getSent(Employee sender) {
        return emailRepository.findAllBySenderEquals(sender);
    }


    @Override
    public List<Email> findAll(Pageable pageable) {
        int pageNo = pageable.getPageNumber();
        return emailRepository.findAll(PageRequest.of(pageNo, 4, Sort.by("id").ascending())).getContent();

    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteEmail(long theId) {
        emailRepository.deleteById(theId);
    }


//    @Override
//    public Email getFile(String fileId) {
//        return emailRepository.findById(fileId)
//         //       .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }

}
