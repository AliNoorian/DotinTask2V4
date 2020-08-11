package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;


    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;

    }


    @GetMapping("/list")
    public ModelAndView listLeaves() {

        ModelAndView modelAndView = new ModelAndView("leaves.jsp");
        // get leaves from db
        int status = 0;    //for requested Leave application
        modelAndView.addObject("leaves", leaveService.findAllLeaves(status));

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("/add/leave.jsp");
        // create model attribute to bind form data
        Leave leave = new Leave();

        modelAndView.addObject("leave", leave);

        return modelAndView;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("leaveId") long theId) {
        ModelAndView modelAndView = new ModelAndView("leaves.jsp");

        // get the leave from the service
        Leave leave = leaveService.findByLeaveId(theId);

        // set leave as a model attribute to pre-populate the form
        modelAndView.addObject("leave", leave);

        // send over to our form
        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveLeave(@Valid @ModelAttribute("leave") Leave leave,
                                  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:leaves.jsp");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("leave", leave);
            return modelAndView;
        }

        if ((leave.getLeaveFrom() == null || leave.getLeaveTo() == null))    //to check dates are not empty
        {
            modelAndView.addObject("message", "هر دو قسمت باید تکمیل شوند");
            modelAndView.addObject("leave", leave);
            return modelAndView;
        } else if (!(leave.getLeaveFrom().matches(leave.getLeaveTo())))    //to check validity of period of dates
        {
            modelAndView.addObject("message", "این زمان امکان پذیر نمیباشد");
            modelAndView.addObject("leave", leave);
            return modelAndView;
        } else if (((leaveService.findAll().stream().anyMatch(i -> i.getLeaveFrom().equals(leave.getLeaveFrom())))))    //to check validity of period of dates
        {
            modelAndView.addObject("message", "این زمان امکان پذیر نمیباشد");
            modelAndView.addObject("leave", leave);
            return modelAndView;

        } else if (((leaveService.findAll().stream().anyMatch(i -> i.getLeaveTo().equals(leave.getLeaveTo())))))    //to check validity of period of dates
        {
            modelAndView.addObject("message", "این تاریخ امکان پذیر نمیباشد");
            modelAndView.addObject("leave", leave);
            return modelAndView;
        }

        modelAndView.addObject("leave", leave);
        return modelAndView;
    }


    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("leaveId") long theId) {
        ModelAndView modelAndView = new ModelAndView("redirect:leaves.jsp");

        // delete the leave
        leaveService.deleteLeave(theId);

        // redirect to /leaves/list
        return modelAndView;

    }


}
