package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private CategoryService categoryService;


    @Autowired
    private CategoryElementService categoryElementService;

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }


    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get employees from db
        List<Employee> theEmployees = employeeService.getAllEmployees();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "/add/employee";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.getEmployee(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "/add/employee";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.addEmployee(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:employees";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.removeEmployee(theId);

        // redirect to /employees/list
        return "redirect:employees";

    }

    @GetMapping("/search")
    public String delete(@RequestParam("employeeName") String theName,
                         Model theModel) {

        // delete the employee
        List<Employee> theEmployees = employeeService.searchBy(theName);

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        // send to /employees/list
        return "employees";

    }

}
