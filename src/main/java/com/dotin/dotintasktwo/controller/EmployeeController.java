package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private final CategoryElementService categoryElementService;
    private final EmployeeService employeeService;
    private final CategoryService categoryService;


    @Autowired
    public EmployeeController(CategoryService categoryService,
                              EmployeeService employeeService,
                              CategoryElementService categoryElementService) {
        this.categoryService = categoryService;
        this.employeeService = employeeService;
        this.categoryElementService = categoryElementService;

    }


    // add mapping for "/list"
    @GetMapping("/list")
    public ModelAndView listEmployees(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("employee/employees.jsp");
        List<Employee> employees = employeeService.findAll(pageable);
        int totalRecords = employeeService.findAll().size();

        modelAndView.addObject("totalRecords", totalRecords);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("employee/addEmployee.jsp");

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        modelAndView.addObject("employee", theEmployee);
        modelAndView.addObject("categoryElements", categoryElementService.getCategoryName(categoryService.findByName("userRole")));
        modelAndView.addObject("managers", employeeService.findManager("MANAGER"));


        return modelAndView;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("employee/addEmployee.jsp");

        modelAndView.addObject("employee", employeeService.findById(theId));
        modelAndView.addObject("categoryElement",
                categoryElementService.getAllCategoryRoleElements());
        modelAndView.addObject("managers", employeeService.findManager("MANAGER"));

        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveEmployee(@ModelAttribute(name = "employee") @Valid Employee theEmployee,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/employee/addEmployee.jsp");
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");

//        if (isEmployeeUpdate) {
//
//            theEmployee.setVersion(theEmployee.getVersion() + 1);
//            theEmployee.setModifiedDate(new Time().getTime());
//            isEmployeeUpdate = false;
//            employeeService.Save(theEmployee);
//            return modelAndView;
//
//        }


//        if (((employeeService.findAll().stream().anyMatch(i -> i.getEmail().equals(theEmployee.getEmail()))))) {
//            ModelAndView modelAndView2 = new ModelAndView("/employee/addEmployee.jsp");
//            modelAndView2.addObject("message", "این ایمیل تکراری می باشد");
//            return modelAndView2;
//        }

            employeeService.Save(theEmployee);
            return modelAndView;
        }
    }



    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");

        // delete the employee
        employeeService.deleteEmployee(theId);

        // redirect to /employees/list
        return modelAndView;

    }

    @GetMapping("/show/{id}")
    public ModelAndView showEmployee(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("/employee/showEmployee.jsp");
        modelAndView.addObject("employee",  employeeService.findById(theId));
        return modelAndView;

    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("employeeName") String theName) {
        ModelAndView modelAndView = new ModelAndView("employee/employees.jsp");


        List<Employee> theEmployees = employeeService.searchBy(theName);

        modelAndView.addObject("employees", theEmployees);

        return modelAndView;

    }


}
