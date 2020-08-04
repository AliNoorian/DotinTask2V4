//package com.dotin.dotintasktwo.controller;
//
//import com.dotin.dotintasktwo.model.Email;
//import com.dotin.dotintasktwo.service.EmailService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/emails")
//public class EmailController {
//
//    private final EmailService emailService;
//
//    public EmailController(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    // add mapping for "/list"
//
//    @GetMapping("/list")
//    public String listemails(Model theModel) {
//
//        // get emails from db
//        List<Email> emails = emailService.getAllEmails();
//
//        // add to the spring model
//        theModel.addAttribute("emails", emails);
//
//        return "emails";
//    }
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//
//        // create model attribute to bind form data
//        Email email = new Email();
//
//        theModel.addAttribute("email", email);
//
//        return "/add/email";
//    }
//
//
//
//
//    @PostMapping("/save")
//    public String saveEmail(@ModelAttribute("email") Email email) {
//
//        // save the email
//        emailService.addEmail(email);
//
//        // use a redirect to prevent duplicate submissions
//        return "redirect:emails";
//    }
//
//
//}
