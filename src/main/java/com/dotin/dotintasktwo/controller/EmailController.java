package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Email;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.EmailService;
import com.dotin.dotintasktwo.service.EmployeeService;
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
import javax.validation.Valid;
import java.sql.Blob;
import java.sql.SQLException;


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
        this.employeeService = employeeService;

    }


    @GetMapping("/list")
    public ModelAndView getInbox(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("/email/inbox.jsp");

        int totalRecords = emailService.getInbox(employeeService.findByName("admin")).size();

        modelAndView.addObject("ReceivedEmails", emailService.getInbox(employeeService.findByName("admin"),pageable));
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/sent")
    public ModelAndView getSent(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("/email/sentBox.jsp");
        int totalRecords = emailService.getSent(employeeService.findByName("admin")).size();

        modelAndView.addObject("sentEmails", emailService.getSent(employeeService.findByName("admin"), pageable));
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/inbox")
    public ModelAndView getInbox(@ModelAttribute("receive") Employee receiver,
                                 @PageableDefault(size = 5) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("/email/inbox.jsp");
        int totalRecords = employeeService.findAll().size();

        modelAndView.addObject("totalRecords", totalRecords);
        modelAndView.addObject("emails", emailService.getInbox(receiver, pageable));

        return modelAndView;
    }


    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("email/addEmail.jsp");
        Email email = new Email();
        modelAndView.addObject("employeeReceivers", employeeService.findAll());
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
    public ModelAndView sendEmail(@ModelAttribute(name = "email") @Valid Email email,
                                  BindingResult bindingResult,
                                  @RequestParam(name = "file") MultipartFile emailFile) throws SQLException {

        if (bindingResult.hasErrors()) {
            logger.info("Send Error!!!");

            return new ModelAndView("/email/addEmail.jsp");
        }

        email.setSender(employeeService.findByName("admin"));

        ModelAndView modelAndView = new ModelAndView("redirect:/emails/list");

        try {
            if (!emailFile.isEmpty()) {
                logger.info("Persisting new file!!!");

                //  Blob blob = Hibernate.getLobCreator(emailFile.getInputStream());
                Blob blob = BlobProxy.generateProxy(emailFile.getBytes());
                if (blob.length() > 0)
                    email.setAttachment(blob);

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


        emailService.addEmail(email);

        return modelAndView;

    }

    @GetMapping("/show/{id}")
    public ModelAndView showEmail(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/email/showEmail.jsp");
        Email email = emailService.getEmail(theId);
        modelAndView.addObject("email", email);
        return modelAndView;

    }

//
//    @GetMapping("/searchEmployee")
//    @ResponseBody
//    public JSONArray searchEmployee(HttpServletRequest request) {
//
//        String param = request.getParameter("param");
//
//        List<Employee> employeeList = employeeService.searchBy(param);
//
//        JSONArray jsonArray = new JSONArray();
//        for (Employee ee : employeeList) {
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("employeeId", ee.getId());
//            jsonObject.put("employeeName", ee.getFirstName() + " " + ee.getLastName());
//            jsonArray.add(jsonObject);
//        }
//
//        return jsonArray;
//    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/emails/list");

        emailService.deleteEmail(theId);

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
