package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView listEmployees(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("employees.jsp");
        List<Employee> employees = employeeService.findAll(pageable);
        int totalRecords = employeeService.findAll().size();

        modelAndView.addObject("employees", employees);
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("/add/employee.jsp");

        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        List<Category> categories = categoryService.getAllCategory();
        if (categories.isEmpty()) {
            Category category1 = new Category();
            category1.setCategoryName("userRole");

            CategoryElement categoryElement1 = new CategoryElement();
            CategoryElement categoryElement2 = new CategoryElement();
            CategoryElement categoryElement3 = new CategoryElement();
            CategoryElement categoryElement4 = new CategoryElement();
            CategoryElement categoryElement5 = new CategoryElement();
            CategoryElement categoryElement6 = new CategoryElement();


            categoryElement1.setName("برنامه نویس");
            categoryElement1.setCode("PROGRAMMER");

            categoryElement2.setName("تستر");
            categoryElement2.setCode("TESTER");


            categoryElement3.setName("غیره");
            categoryElement3.setCode("OTHER");

            categoryElement4.setName("مدیر برنامه نویس");
            categoryElement4.setCode("PROGRAMMER_MANGER");

            categoryElement5.setName("مدیر تستر");
            categoryElement5.setCode("TESTER_MANAGER");

            categoryElement6.setName("مدیر غیره");
            categoryElement6.setCode("OTHER_manager");

            categoryService.addCategory(category1);

            categoryElementService.addCategoryElement(categoryElement1);
            categoryElementService.addCategoryElement(categoryElement2);
            categoryElementService.addCategoryElement(categoryElement3);
            categoryElementService.addCategoryElement(categoryElement4);
            categoryElementService.addCategoryElement(categoryElement5);
            categoryElementService.addCategoryElement(categoryElement6);

        }
        modelAndView.addObject("employee", theEmployee);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("categoryElements", categoryElementService.getAllCategoryElements());
        modelAndView.addObject("managers", employeeService.findAll());


        return modelAndView;
    }

    @RequestMapping("/showFormForUpdate/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/add/employee.jsp");

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        List<Employee> managers = employeeService.findManager();

        //set employee as a model attribute to pre-populate the form
        modelAndView.addObject("employee", theEmployee);
        modelAndView.addObject("categories", categoryService.getAllCategory());
        modelAndView.addObject("categoryElement", categoryElementService.getAllCategoryElements());
        modelAndView.addObject("managers", managers);

        // send over to our form
        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");

        // save the employee
        employeeService.Save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return modelAndView;
    }


    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");

        // delete the employee
        employeeService.deleteEmployee(theId);

        // redirect to /employees/list
        return modelAndView;

    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("employeeName") String theName) {
        ModelAndView modelAndView = new ModelAndView("employees.jsp");


        // delete the employee
        List<Employee> theEmployees = employeeService.searchBy(theName);

        // add to the spring model
        modelAndView.addObject("employees", theEmployees);

        // send to /employees/list
        return modelAndView;

    }


}
