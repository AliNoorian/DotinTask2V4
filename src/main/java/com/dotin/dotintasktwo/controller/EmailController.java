package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.utility.Time;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


import java.io.IOException;
import java.sql.Blob;
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
    public ModelAndView showFormForAdd(@RequestParam(value = "file") MultipartFile file) throws IOException {

        ModelAndView modelAndView = new ModelAndView("/add/email.jsp");
        Time time = new Time();
        Email email = new Email();
        email.setCreateDate(time.getTime());
        email.setVersion(1);
        email.setActive(true);
        Blob blobFile = BlobProxy.generateProxy(file.getBytes());
        email.setAttachment(blobFile);
//        String selectedEmployeeStr = request.getParameter("selectedIds");
//        String[] selectedEmployeeArray = selectedEmployeeStr.split(",");
//        List<Employee> receiverList = employeeService.findAll();
//        for (String employeeId : selectedEmployeeArray) {
//            Employee selectedEmployee = employeeService.findById(Long.getLong(employeeId));
//            receiverList.add(selectedEmployee);
//        }

        modelAndView.addObject("email", email);


        return modelAndView;
    }


    @PostMapping("/send")
    public ModelAndView sendEmail(@ModelAttribute("email") Email email) {

        ModelAndView modelAndView = new ModelAndView("redirect:emails.jsp");

        // save the email
        emailService.addEmail(email);

        // use a redirect to prevent duplicate submissions
        return modelAndView;
    }


}
