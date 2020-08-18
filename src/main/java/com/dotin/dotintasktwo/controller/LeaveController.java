package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.LeaveService;
import com.dotin.dotintasktwo.utility.DateUtil;
import com.dotin.dotintasktwo.utility.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;
    private final CategoryElementService categoryElementService;

    @Autowired
    public LeaveController(LeaveService leaveService,
                           CategoryElementService categoryElementService) {
        this.leaveService = leaveService;
        this.categoryElementService = categoryElementService;
    }


    @GetMapping("/list")
    public ModelAndView listLeaves(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("leave/leaves.jsp");
        List<Leave> leaves = leaveService.findAll(pageable);
        int totalRecords = leaveService.findAllPending().size();

        modelAndView.addObject("leaves", leaves);
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/eList")
    public ModelAndView getLeaves(@ModelAttribute("e") Employee employee,
                                  @PageableDefault(size = 5) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("leave/leaves.jsp");

        modelAndView.addObject("leaves", leaveService.getLeaves(employee, pageable));

        return modelAndView;
    }


    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("leave/addLeave.jsp");

        Time time = new Time();
        Leave leave = new Leave();
        leave.setCreateDate(time.getTime());
        leave.setActive(true);
        leave.setVersion(1);


        leave.setLeaveStatus(categoryElementService.getPendingCategoryElement());

        modelAndView.addObject("leave", leave);

        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveLeave(@ModelAttribute(name = "leave") @Valid Leave leave,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/leave/addLeave.jsp");
        }
        if (!leaveService.findAllStatus(categoryElementService.getCategoryElementByCode("APPROVED")).isEmpty()) {
            DateUtil dateUtil = new DateUtil();
            if (dateUtil.checkDate(leaveService.findAllStatus(categoryElementService.getCategoryElementByCode("APPROVED")),
                    leave.getLeaveFrom(), leave.getLeaveTo())) {

                ModelAndView modelAndView = new ModelAndView("/leave/addLeave.jsp");
                modelAndView.addObject("message", "این تاریخ امکان پذیر نمیباشد");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/leaves/list");

        modelAndView.addObject("leave", leave);
        leave.setLeaveStatus(categoryElementService.getCategoryElementByCode("PENDING"));
        leaveService.Save(leave);
        return modelAndView;
    }

    @GetMapping("/setApproved/{id}")
    public ModelAndView setApproved(@PathVariable("id") long theId) {
        Leave leave = leaveService.findByLeaveId(theId);
        if (!leaveService.findAllApproved().isEmpty()) {
            DateUtil dateUtil = new DateUtil();
            if (dateUtil.checkDate(leaveService.findAllApproved(), leave.getLeaveFrom(), leave.getLeaveTo())) {
                ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");
                modelAndView.addObject("message", "زمان درخواست شده با زمان مرخصی های موافقت شده جدید، تداخل دارد");
                return modelAndView;
            }
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/leaves/list");
        leaveService.grantLeave(theId);

        return modelAndView;
    }

    @GetMapping("/setRejected/{id}")
    public ModelAndView setRejected(@PathVariable("id") long theId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/leaves/list");

        leaveService.rejectLeave(theId);

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("leaveId") long theId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");
        leaveService.deleteLeave(theId);
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showLeave(@RequestParam("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/leave/showLeave.jsp");
        Leave leave = leaveService.findByLeaveId(theId);
        modelAndView.addObject("leave", leave);
        return modelAndView;

    }


}
