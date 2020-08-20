package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.EmployeeService;
import com.dotin.dotintasktwo.service.LeaveService;
import com.dotin.dotintasktwo.utility.DateUtil;
import com.dotin.dotintasktwo.utility.Time;
import org.apache.log4j.Logger;
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
    private final Logger logger = Logger.getLogger(LeaveController.class);

    private final LeaveService leaveService;
    private final EmployeeService employeeService;
    private final CategoryElementService categoryElementService;

    @Autowired
    public LeaveController(LeaveService leaveService,
                           EmployeeService employeeService,
                           CategoryElementService categoryElementService) {
        this.leaveService = leaveService;
        this.employeeService=employeeService;
        this.categoryElementService = categoryElementService;
    }


    @GetMapping("/list")
    public ModelAndView listLeaves(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("leave/leaves.jsp");
        List<Leave> leaves = leaveService.findAll(pageable);
        int totalRecords = leaveService.findAllPending().size();

        modelAndView.addObject("leaves", leaves);
        modelAndView.addObject("totalRecords", totalRecords);
        modelAndView.addObject("message","برای اعمال درخواست میتوانید بر روی دکمه های روبروی هر مرخصی کلیک کنید");

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

        Leave leave = new Leave();

        modelAndView.addObject("leave", leave);
        modelAndView.addObject("employees",employeeService.findAll());

        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveLeave(@ModelAttribute(name = "leave") @Valid Leave leave,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.info("Save leave Error!!!");
            return new ModelAndView("/leave/addLeave.jsp");
        }
        if (!leaveService.findAllStatus(categoryElementService.getCategoryElementByCode("APPROVED")).isEmpty()) {

            if (new DateUtil().checkDate(leaveService.findAllStatus(categoryElementService.getCategoryElementByCode("APPROVED")),
                    leave.getLeaveFrom(), leave.getLeaveTo())) {

                ModelAndView modelAndView = new ModelAndView("redirect:/leaves/showFormForAdd");
                modelAndView.addObject("message", "تاریخ شما با مرخصی تایید شده دیگر، تداخل زمانی دارد!");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/leaves/list");

        modelAndView.addObject("leave", leave);
        leave.setEmployee(employeeService.findByName("admin"));
        leave.setCreateDate(new Time().getTime());
        leave.setActive(true);
        leave.setVersion(1);
        leave.setLeaveStatus(categoryElementService.getCategoryElementByCode("PENDING"));
        leaveService.Save(leave);
        return modelAndView;
    }

    @GetMapping("/setApproved/{id}")
    public ModelAndView setApproved(@PathVariable("id") long theId) {
        Leave leave = leaveService.findByLeaveId(theId);
        if (!leaveService.findAllApproved().isEmpty()) {
            if (new DateUtil().checkDate(leaveService.findAllApproved(), leave.getLeaveFrom(), leave.getLeaveTo())) {
                ModelAndView modelAndView = new ModelAndView("redirect:/leaves/list");
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

    @GetMapping("/show/{id}")
    public ModelAndView showLeave(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/leave/showLeave.jsp");
        Leave leave = leaveService.findByLeaveId(theId);
        modelAndView.addObject("leave", leave);
        modelAndView.addObject("message","جهت ثبت یا رد درخواست میتوانید از قسمت پایین فرم استفاده کنید");
        return modelAndView;

    }


}
