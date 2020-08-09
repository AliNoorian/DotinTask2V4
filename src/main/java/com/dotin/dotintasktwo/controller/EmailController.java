package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.service.EmployeeService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;


@Controller
@RequestMapping("/emails")
public class EmailController {


    private final EmailService emailService;
    private final EmployeeService employeeService;



    @Autowired
    public EmailController(EmailService emailService,
                           EmployeeService employeeService) {
        this.emailService = emailService;

        this.employeeService = employeeService;
    }


    @GetMapping("/list")
    public ModelAndView getInbox(Model theModel) {

        ModelAndView modelAndView = new ModelAndView("emails.jsp");

        // get emails from db
        List<Email> emails = emailService.getAllEmails();

        // add to the spring model
        theModel.addAttribute("emails", emails);

        return modelAndView;
    }

    @GetMapping("/outbox")
    public ModelAndView getOutbox(Model theModel) {

        ModelAndView modelAndView = new ModelAndView("emails.jsp");

        // get emails from db
        List<Email> emails = emailService.getAllEmails();

        // add to the spring model
        theModel.addAttribute("emails", emails);

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(Model theModel,
                                 HttpServletRequest request,
                                 @RequestParam(value = "file") MultipartFile file) throws IOException {

        ModelAndView modelAndView = new ModelAndView("/add/email.jsp");

        Email email = new Email();
        Blob blobFile = BlobProxy.generateProxy(file.getBytes());
        email.setAttachment(blobFile);
        String selectedEmployeeStr = request.getParameter("selectedIds");
        String[] selectedEmployeeArray = selectedEmployeeStr.split(",");
        List<Employee> receiverList = employeeService.findAll();
        for (String employeeId : selectedEmployeeArray) {
            Employee selectedEmployee = employeeService.findById(Long.getLong(employeeId));
            receiverList.add(selectedEmployee);
        }

        // create model attribute to bind form data
        theModel.addAttribute("email", email);


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
