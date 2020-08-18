package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.service.EmployeeService;
import com.dotin.dotintasktwo.utility.Time;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/emails")
public class EmailController {
    private final Logger logger = Logger.getLogger(EmailController.class);

    private final EmailService emailService;
    private final EmployeeService employeeService;



    @Autowired
    public EmailController(EmailService emailService,
                           EmployeeService employeeService) {
        this.emailService = emailService;
        this.employeeService=employeeService;

    }


    @GetMapping("/list")
    public ModelAndView getInbox(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("email/emails.jsp");

        List<Email> emails = emailService.findAll(pageable);
        int totalRecords = emailService.findAll().size();

        modelAndView.addObject("emails", emails);
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/sent")
    public ModelAndView getSent(@ModelAttribute("s") Employee sender,
                                  @PageableDefault(size = 5) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("email/emails.jsp");

        modelAndView.addObject("emails", emailService.getSent(sender,pageable));

        return modelAndView;
    }

    @GetMapping("/inbox")
    public ModelAndView getInbox(@ModelAttribute("r") Employee receiver,
                                  @PageableDefault(size = 5) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("email/emails.jsp");

        modelAndView.addObject("emails", emailService.getInbox(receiver,pageable));

        return modelAndView;
    }




    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("email/addEmail.jsp");
        Time time = new Time();
        Email email = new Email();
        email.setCreateDate(time.getTime());
        email.setVersion(1);
        email.setActive(true);


        modelAndView.addObject("email", email);


        return modelAndView;
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
    public ModelAndView sendEmail(@ModelAttribute(name="email")@Valid Email email,
                                  BindingResult bindingResult,
                                  @ModelAttribute(name="file") MultipartFile file,
                                  HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/email/addEmail.jsp");
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/emails/list");


            String employees = request.getParameter("receivers");

            String[] splitEmployees = employees.split(",");

            List<Employee> receiverList = new ArrayList<>();

            for (String employeeId : splitEmployees) {
                Employee selectedEmployee = employeeService.findById(Long.parseLong(employeeId));
                receiverList.add(selectedEmployee);
            }


            email.setSender(employeeService.findByName("admin"));
            email.setReceivers(receiverList);

//            try {
//                Blob blobFile = BlobProxy.generateProxy(file.getBytes());
//                if (blobFile.length() > 0)
//                    email.setAttachment(blobFile);
//
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            }



        emailService.addEmail(email);

        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showEmail(@RequestParam("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/email/showEmail.jsp");
        Email email=emailService.getEmail(theId);
        modelAndView.addObject("email",email);
        return modelAndView;

    }

//    @PostMapping("/reply")
//    public ModelAndView replyEmail(@Valid @ModelAttribute("email") Email email,
//                                   @PathVariable("senderId") long senderId,
//                                   BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/emails/list");
//
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("/email/addEmail.jsp");
//        }
//
//        Employee employeeSender= employeeService.findById(senderId);
//        emailService.addEmail(email);
//
//        // use a redirect to prevent duplicate submissions
//        return modelAndView;
//    }

}
