package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.utility.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


import java.util.List;


@Controller
@RequestMapping("/emails")
public class EmailController {


    private final EmailService emailService;



    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @GetMapping("/list")
    public ModelAndView getInbox(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("emails.jsp");

        List<Email> emails = emailService.findAll(pageable);
        int totalRecords = emailService.findAll().size();

        modelAndView.addObject("emails", emails);
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/outbox")
    public ModelAndView getOutbox() {

        ModelAndView modelAndView = new ModelAndView("emails.jsp");

        // get emails from db
        List<Email> emails = emailService.getAllEmails();

        modelAndView.addObject("emails", emails);

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("/add/email.jsp");
        Time time = new Time();
        Email email = new Email();
        email.setCreateDate(time.getTime());
        email.setVersion(1);
        email.setActive(true);


        modelAndView.addObject("email", email);


        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/upload")
    public void uploadFile(@RequestParam("emailId") long emailId,
                           @RequestParam("file") MultipartFile file) {

        emailService.storeFile(file, emailId);


    }

//    @PostMapping("/uploadMultipleFiles")
//    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
//                                    @RequestParam(""emailId") long emailId) {
//         Arrays.asList(files,emailId)
//                .stream()
//                .map(file -> uploadFile(emailId,file))
//                .collect(Collectors.toList());
//    }

    @PostMapping("/send")
    public ModelAndView sendEmail(@ModelAttribute("email") Email email) {

        ModelAndView modelAndView = new ModelAndView("redirect:emails.jsp");

        // save the email
        emailService.addEmail(email);

        // use a redirect to prevent duplicate submissions
        return modelAndView;
    }


}
