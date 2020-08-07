package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.service.EmployeeService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;


@Controller
@RequestMapping("/emails")
public class EmailController {


    private final EmailService emailService;
    private final EmployeeService employeeService;
    private final CategoryService categoryService;
    private final CategoryElementService categoryElementService;


    @Autowired
    public EmailController(EmailService emailService,
                           CategoryElementService categoryElementService,
                           CategoryService categoryService,
                           EmployeeService employeeService) {
        this.emailService = emailService;
        this.categoryElementService = categoryElementService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }


    @GetMapping("/inbox")
    public String getInbox(Model theModel) {

        // get emails from db
        List<Email> emails = emailService.getAllEmails();

        // add to the spring model
        theModel.addAttribute("emails", emails);

        return "emails";
    }

    @GetMapping("/outbox")
    public String getOutbox(Model theModel) {

        // get emails from db
        List<Email> emails = emailService.getAllEmails();

        // add to the spring model
        theModel.addAttribute("emails", emails);

        return "emails";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel,
                                 HttpServletRequest request,
                                 @RequestParam(value = "file") MultipartFile file) throws IOException {


        Email email = new Email();
        Blob blobFile = BlobProxy.generateProxy(file.getBytes());
        email.setAttachment(blobFile);
        String selectedEmployeeStr = request.getParameter("selectedIds");
        String[] selectedEmployeeArray = selectedEmployeeStr.split(",");
        List<Employee> receiverList = employeeService.getAllEmployees();
        for (String employeeId : selectedEmployeeArray) {
            Employee selectedEmployee = employeeService.getEmployee(Long.getLong(employeeId));
            receiverList.add(selectedEmployee);
        }

        // create model attribute to bind form data
        theModel.addAttribute("email", email);


        return "/add/email";
    }


    @PostMapping("/send")
    public String sendEmail(@ModelAttribute("email") Email email) {

        // save the email
        emailService.addEmail(email);

        // use a redirect to prevent duplicate submissions
        return "redirect:emails";
    }


}
