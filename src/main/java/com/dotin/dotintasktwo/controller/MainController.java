package com.dotin.dotintasktwo.controller;


import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmployeeService;
import com.dotin.dotintasktwo.utility.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {


    private final CategoryService categoryService;
    private final EmployeeService employeeService;
    private final CategoryElementService categoryElementService;

    @Autowired
    public MainController(EmployeeService employeeService,
                          CategoryService categoryService,
                          CategoryElementService categoryElementService) {
        this.employeeService = employeeService;
        this.categoryService = categoryService;
        this.categoryElementService = categoryElementService;
    }


    @GetMapping("/")
    public ModelAndView homePage() {

        ModelAndView modelAndView = new ModelAndView("index.jsp");


        if (categoryService.getAllCategory().stream().noneMatch(
                i -> i.getCategoryName().equals("userRole"))) {
           Time time = new Time();
            Category category1 = new Category();
            category1.setCategoryName("userRole");
            category1.setActive(true);
            category1.setVersion(1);
            categoryService.addCategory(category1);
            System.out.println("======================================");
            System.out.println(time);
            System.out.println("======================================");
            Category category2 = new Category();
            category2.setCategoryName("leaveStatus");
            category2.setActive(true);
            category2.setVersion(1);
            categoryService.addCategory(category2);

//            CategoryElement categoryElement1 = new CategoryElement();
//            CategoryElement categoryElement2 = new CategoryElement();
//            CategoryElement categoryElement3 = new CategoryElement();
//            CategoryElement categoryElement4 = new CategoryElement();
//            CategoryElement categoryElement5 = new CategoryElement();
//            CategoryElement categoryElement6 = new CategoryElement();
//            CategoryElement categoryElement7 = new CategoryElement();
//
//
//            categoryElement1.setName("برنامه نویس");
//            categoryElement1.setCode("PROGRAMMER");
//            categoryElement1.setCategory(categoryService.findByName("userRole"));
//
//            categoryElement2.setName("تستر");
//            categoryElement2.setCode("TESTER");
//            categoryElement2.setCategory(categoryService.findByName("userRole"));
//
//
//            categoryElement3.setName("غیره");
//            categoryElement3.setCode("OTHER");
//            categoryElement3.setCategory(categoryService.findByName("userRole"));
//
//            categoryElement4.setName("مدیر برنامه نویس");
//            categoryElement4.setCode("PROGRAMMER_MANAGER");
//            categoryElement4.setCategory(categoryService.findByName("userRole"));
//
//            categoryElement5.setName("مدیر تستر");
//            categoryElement5.setCode("TESTER_MANAGER");
//            categoryElement5.setCategory(categoryService.findByName("userRole"));
//
//            categoryElement6.setName("مدیر غیره");
//            categoryElement6.setCode("OTHER_MANAGER");
//            categoryElement6.setCategory(categoryService.findByName("userRole"));
//
//            categoryElement7.setName("ادمین");
//            categoryElement7.setCode("ADMIN");
//            categoryElement7.setCategory(categoryService.findByName("userRole"));
//
//            categoryElementService.addCategoryElement(categoryElement1);
//            categoryElementService.addCategoryElement(categoryElement2);
//            categoryElementService.addCategoryElement(categoryElement3);
//            categoryElementService.addCategoryElement(categoryElement4);
//            categoryElementService.addCategoryElement(categoryElement5);
//            categoryElementService.addCategoryElement(categoryElement6);
//            categoryElementService.addCategoryElement(categoryElement7);
//
//            Employee employee = new Employee();
//            employee.setFirstName("admin");
//            employee.setLastName("admin");
//            employee.setEmail("admin@dotin.ir");
//            employee.setVersion(1);
//            employee.setCreateDate(new Time().getTime());
//            employee.setEmployeeGender("آقا");
//            employee.setActive(true);
//            employee.setEmployeeRole(categoryElementService.getCategoryElementByCode("ADMIN"));
//            employeeService.Save(employee);

        }

//        if (categoryService.getAllCategory().stream().noneMatch(
//                i -> i.getCategoryName().equals("leaveStatus"))) {
//
//
//
//
//            CategoryElement categoryElement1 = new CategoryElement();
//            CategoryElement categoryElement2 = new CategoryElement();
//            CategoryElement categoryElement3 = new CategoryElement();
//
//            categoryElement1.setName("تایید");
//            categoryElement1.setCode("APPROVED");
//            categoryElement1.setCategory(categoryService.findByName("leaveStatus"));
//
//            categoryElement2.setName("رد");
//            categoryElement2.setCode("REJECTED");
//            categoryElement2.setCategory(categoryService.findByName("leaveStatus"));
//
//            categoryElement3.setName("در دست بررسی");
//            categoryElement3.setCode("PENDING");
//            categoryElement3.setCategory(categoryService.findByName("leaveStatus"));
//
//            categoryElementService.addCategoryElement(categoryElement1);
//            categoryElementService.addCategoryElement(categoryElement2);
//            categoryElementService.addCategoryElement(categoryElement3);
//
//        }


        return modelAndView;

    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:index.jsp");
        httpSession.invalidate();
        return modelAndView;
    }
}