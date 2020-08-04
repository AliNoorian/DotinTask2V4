//package com.dotin.dotintasktwo.controller.leave;
//
//
//import com.dotin.dotintasktwo.model.Leave;
//import com.dotin.dotintasktwo.repository.LeaveRepository;
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
//public class MakeLeave {
//
//    private Leave leave;
//
//    private LeaveRepository leaveRepository;
//
//    @Autowired
//    public void setLeaveRepository(LeaveRepository leaveRepository) {
//        this.leaveRepository = leaveRepository;
//    }
//
//    @Autowired
//    public void setLeave(Leave leave) {
//        this.leave = leave;
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("leave",leave);
//        return "add/leave";
//    }
//
//    @PostMapping("/register")
//    public String registerSubmit(@ModelAttribute @Valid Leave leave,
//                                 BindingResult bindingResult,
//                                 Model model,
//                                 HttpSession httpSession){
//        return "getString(leave, bindingResult, model, httpSession, leaveRepository)";
//    }
//
//
//}
