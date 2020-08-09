package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // add mapping for "/list"

    @GetMapping("/list")
    public ModelAndView listLeaves(Model theModel) {

        ModelAndView modelAndView = new ModelAndView("leaves.jsp");
        // get leaves from db
        int status = 0;    //for requested Leave application
        theModel.addAttribute("leaves", leaveService.findAllLeaves(status));

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(Model theModel) {

        ModelAndView modelAndView = new ModelAndView("/add/leave.jsp");
        // create model attribute to bind form data
        Leave leave = new Leave();

        theModel.addAttribute("leave", leave);

        return modelAndView;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("leaveId") long theId,
                                          Model theModel) {
        ModelAndView modelAndView = new ModelAndView("leaves.jsp");

        // get the leave from the service
        Leave theleave = leaveService.findByLeaveId(theId);

        // set leave as a model attribute to pre-populate the form
        theModel.addAttribute("leave", theleave);

        // send over to our form
        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveLeave(@Valid @ModelAttribute("leave") Leave leave,
                                  BindingResult bindingResult,
                                  Model model) {
        ModelAndView modelAndView = new ModelAndView("redirect:leaves.jsp");

        if (bindingResult.hasErrors()) {
            model.addAttribute("leave", leave);
            return modelAndView;
        }

        if ((leave.getLeaveFrom() == null || leave.getLeaveTo() == null))    //to check dates are not empty
        {
            model.addAttribute("message", "هر دو قسمت باید تکمیل شوند");
            model.addAttribute("leave", leave);
            return modelAndView;
        } else if (!(leave.getLeaveFrom().matches(leave.getLeaveTo())))    //to check validity of period of dates
        {
            model.addAttribute("message", "این زمان امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return modelAndView;
        } else if (((leaveService.findAll().stream().anyMatch(i -> i.getLeaveFrom().equals(leave.getLeaveFrom())))))    //to check validity of period of dates
        {
            model.addAttribute("message", "این زمان امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return modelAndView;

        } else if (((leaveService.findAll().stream().anyMatch(i -> i.getLeaveTo().equals(leave.getLeaveTo())))))    //to check validity of period of dates
        {
            model.addAttribute("message", "این تاریخ امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return modelAndView;
        }

        model.addAttribute("leave", leave);
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
