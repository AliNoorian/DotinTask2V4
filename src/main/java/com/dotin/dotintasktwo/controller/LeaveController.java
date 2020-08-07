package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String listLeaves(Model theModel) {

        // get leaves from db
        long status=0;	//for requested Leave application
        theModel.addAttribute("leaves",leaveService.findByLeaveId(status));

        return "leaves";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Leave leave = new Leave();

        theModel.addAttribute("leave", leave);

        return "/add/leave";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("leaveId") int theId,
                                    Model theModel) {

        // get the leave from the service
        Leave theleave = leaveService.getLeave(theId);

        // set leave as a model attribute to pre-populate the form
        theModel.addAttribute("leave", theleave);

        // send over to our form
        return "leaves";
    }


    @PostMapping("/save")
    public String saveLeave(@Valid @ModelAttribute("leave") Leave leave,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("leave", leave);
            return "redirect:leaves";
        }

        if ((leave.getLeaveFrom() == null || leave.getLeaveTo() == null))    //to check dates are not empty
        {
            model.addAttribute("message", "هر دو قسمت باید تکمیل شوند");
            model.addAttribute("leave", leave);
            return "redirect:leaves";
        } else if (!(leave.getLeaveFrom().matches(leave.getLeaveTo())))    //to check validity of period of dates
        {
            model.addAttribute("message", "این زمان امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return "redirect:leaves";
        } else if (((leaveService.getAllLeaves().stream().anyMatch(i -> i.getLeaveFrom().equals(leave.getLeaveFrom())))))    //to check validity of period of dates
        {
            model.addAttribute("message", "این زمان امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return "redirect:leaves";

        } else if (((leaveService.getAllLeaves().stream().anyMatch(i -> i.getLeaveTo().equals(leave.getLeaveTo())))))    //to check validity of period of dates
        {
            model.addAttribute("message", "این تاریخ امکان پذیر نمیباشد");
            model.addAttribute("leave", leave);
            return "redirect:leaves";
        }

        model.addAttribute("leave", leave);
        return "redirect:leaves";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("leaveId") int theId) {

        // delete the leave
        leaveService.removeLeave(theId);

        // redirect to /leaves/list
        return "redirect:leaves";

    }


}
