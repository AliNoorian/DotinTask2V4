package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
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


    private final CategoryService categoryService;
    private final CategoryElementService categoryElementService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              CategoryElementService categoryElementService,
                              CategoryService categoryService) {
        this.employeeService = employeeService;
        this.categoryElementService = categoryElementService;
        this.categoryService = categoryService;

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
        List<Category> categoryServices = categoryService.getAllCategory();
        List<CategoryElement> CategoryElementServices = categoryElementService.getAllCategoryElements();
        List<Employee> managers = employeeService.findManager();

        theModel.addAttribute("employee", theEmployee);
        theModel.addAttribute("categories", categoryServices);
        theModel.addAttribute("categoryElements", CategoryElementServices);
        theModel.addAttribute("managers", managers);


        return "/add/employee";
    }

    @RequestMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long theId,
                                    Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.getEmployee(theId);
        List<Category> theCategoryServices = categoryService.getAllCategory();
        List<CategoryElement> theCategoryElementServices = categoryElementService.getAllCategoryElements();
        List<Employee> managers = employeeService.findManager();

        //set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);
        theModel.addAttribute("categories", theCategoryServices);
        theModel.addAttribute("categoryElement", theCategoryElementServices);
        theModel.addAttribute("managers", managers);

        // send over to our form
        return "/add/employee";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.addEmployee(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long theId) {

        // delete the employee
        employeeService.removeEmployee(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }

    @GetMapping("/search")
    public String search(@RequestParam("employeeName") String theName,
                         Model theModel) {

        // delete the employee
        List<Employee> theEmployees = employeeService.searchBy(theName);

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        // send to /employees/list
        return "employees";

    }

}
