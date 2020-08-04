//package com.dotin.dotintasktwo.controller;
//
//import com.dotin.dotintasktwo.model.Leave;
//import com.dotin.dotintasktwo.service.LeaveService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/leaves")
//public class LeaveController {
//
//    private final LeaveService leaveService;
//
//    public LeaveController(LeaveService leaveService) {
//        this.leaveService = leaveService;
//    }
//
//    // add mapping for "/list"
//
//    @GetMapping("/list")
//    public String listLeaves(Model theModel) {
//
//        // get leaves from db
//        List<Leave> leaves = leaveService.getAllLeaves();
//
//        // add to the spring model
//        theModel.addAttribute("leaves", leaves);
//
//        return "leaves";
//    }
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//
//        // create model attribute to bind form data
//        Leave leave = new Leave();
//
//        theModel.addAttribute("leave", leave);
//
//        return "/add/leave";
//    }
//
//    @GetMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("leaveId") int theId,
//                                    Model theModel) {
//
//        // get the leave from the service
//        Leave theleave = leaveService.getLeave(theId);
//
//        // set leave as a model attribute to pre-populate the form
//        theModel.addAttribute("leave", theleave);
//
//        // send over to our form
//        return "leaves";
//    }
//
//
//    @PostMapping("/save")
//    public String saveLeave(@ModelAttribute("leave") Leave leave) {
//
//        // save the leave
//        leaveService.addLeave(leave);
//
//        // use a redirect to prevent duplicate submissions
//        return "redirect:leaves";
//    }
//
//
//    @GetMapping("/delete")
//    public String delete(@RequestParam("leaveId") int theId) {
//
//        // delete the leave
//        leaveService.removeLeave(theId);
//
//        // redirect to /leaves/list
//        return "redirect:leaves";
//
//    }
//
//
//
//}
