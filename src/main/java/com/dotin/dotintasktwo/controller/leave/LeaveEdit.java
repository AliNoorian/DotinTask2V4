//package com.dotin.dotintasktwo.controller.leave;
//
//import com.dotin.dotintasktwo.model.Leave;
//import com.dotin.dotintasktwo.repository.LeaveRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class LeaveEdit {
//
//    private LeaveRepository leaveRepository;
//    private Leave leave;
//
//    @Autowired
//    public void setLeave(Leave leave) {
//        this.leave = leave;
//    }
//
//    @Autowired
//    public void setLeaveRepository(LeaveRepository leaveRepository) {
//        this.leaveRepository = leaveRepository;
//    }
//
//    @GetMapping("/edit")
//    public String editPage(HttpSession httpSession, Model model){
//        if (leaveRepository.findById(Long.valueOf((Long) httpSession.getAttribute("Id"))).isPresent()){
//            leave = leaveRepository.findById(Long.valueOf((Long) httpSession.getAttribute("Id"))).get();
//            model.addAttribute("leave",leave);
//            return "leaves";
//        }
//        return "redirect:/index";
//
//    }
//
//    @PostMapping("/edit")
//    public String editSubmit(@ModelAttribute Leave leave,
//                             HttpSession httpSession,
//                             Model model){
//        return "getString(leave, httpSession, model, leaveRepository)";
//    }
//}
