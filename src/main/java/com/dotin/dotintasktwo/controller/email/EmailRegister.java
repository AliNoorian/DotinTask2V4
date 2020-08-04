//package com.dotin.dotintasktwo.controller.email;
//
//
//import com.dotin.dotintasktwo.model.Email;
//import com.dotin.dotintasktwo.repository.EmailRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//@Controller
//public class EmailRegister {
//
//    private Email email;
//
//    private EmailRepository emailRepository;
//
//    @Autowired
//    public void setEmailRepository(EmailRepository emailRepository) {
//        this.emailRepository = emailRepository;
//    }
//
//    @Autowired
//    public void setEmail(Email email) {
//        this.email = email;
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("email",email);
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerSubmit(@ModelAttribute @Valid Email email,
//                                 BindingResult bindingResult,
//                                 Model model,
//                                 HttpSession httpSession){
//        if (bindingResult.hasErrors()){
//            return "register";
//        }
//        if (emailRepository.findById(email.getId()).isPresent()){
//            model.addAttribute("emailStatus","duplicate");
//            return "register";
//        }
//        httpSession.setAttribute("Id",email.getId());
//        httpSession.setMaxInactiveInterval(24 * 60 * 60);
//        emailRepository.save(email);
//        return "redirect:/index";
//    }
//}
