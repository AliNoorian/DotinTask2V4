package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.utility.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


import javax.validation.Valid;
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
    public ModelAndView sendEmail(@Valid @ModelAttribute("email") Email email,
                                  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/emails/list");

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/email/addEmail.jsp");
        }

        // save the email
        emailService.addEmail(email);

        // use a redirect to prevent duplicate submissions
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
