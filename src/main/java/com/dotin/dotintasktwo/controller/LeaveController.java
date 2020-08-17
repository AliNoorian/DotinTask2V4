package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
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
    private final CategoryService categoryService;
    private final CategoryElementService categoryElementService;

    @Autowired
    public LeaveController(LeaveService leaveService,
                           CategoryService categoryService,
                           CategoryElementService categoryElementService) {
        this.leaveService = leaveService;
        this.categoryService = categoryService;
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

        if (categoryService.getAllCategory().stream().noneMatch(
                i -> i.getCategoryName().equals("leaveStatus"))) {

            Category category1 = new Category();
            category1.setCategoryName("leaveStatus");
            categoryService.addCategory(category1);


            CategoryElement categoryElement1 = new CategoryElement();
            CategoryElement categoryElement2 = new CategoryElement();
            CategoryElement categoryElement3 = new CategoryElement();

            categoryElement1.setName("تایید");
            categoryElement1.setCode("APPROVED");
            categoryElement1.setCategory(category1);

            categoryElement2.setName("رد");
            categoryElement2.setCode("REJECTED");
            categoryElement2.setCategory(category1);

            categoryElement3.setName("در دست بررسی");
            categoryElement3.setCode("PENDING");
            categoryElement3.setCategory(category1);

            categoryElementService.addCategoryElement(categoryElement1);
            categoryElementService.addCategoryElement(categoryElement2);
            categoryElementService.addCategoryElement(categoryElement3);

        }
        leave.setLeaveStatus(categoryElementService.getPendingCategoryElement());

        modelAndView.addObject("leave", leave);

        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveLeave(@Valid @ModelAttribute("leave") Leave leave,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/leave/addLeave.jsp");
        }
        if (!leaveService.findAllApproved().isEmpty()) {
            DateUtil dateUtil = new DateUtil();
            if (dateUtil.checkDate(leaveService.findAllApproved(), leave.getLeaveFrom(), leave.getLeaveTo())) {
                ModelAndView modelAndView = new ModelAndView("/leave/addLeave.jsp");
                modelAndView.addObject("message", "این تاریخ امکان پذیر نمیباشد");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");

        modelAndView.addObject("leave", leave);
        leaveService.Save(leave);
        return modelAndView;
    }

    @GetMapping("/setApproved")
    public ModelAndView setApproved(@RequestParam("leaveId") long theId) {
        Leave leave = leaveService.findByLeaveId(theId);
        if (!leaveService.findAllApproved().isEmpty()) {
            DateUtil dateUtil = new DateUtil();
            if (dateUtil.checkDate(leaveService.findAllApproved(), leave.getLeaveFrom(), leave.getLeaveTo())) {
                ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");
                modelAndView.addObject("message", "زمان درخواست شده با زمان مرخصی های موافقت شده جدید، تداخل دارد");
                return modelAndView;
            }
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");
        leaveService.grantLeave(theId);

        return modelAndView;
    }

    @GetMapping("/setRejected")
    public ModelAndView setRejected(@RequestParam("leaveId") long theId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/leave/leaves.jsp");

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
