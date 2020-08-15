package com.dotin.dotintasktwo.controller;

import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.service.CategoryElementService;
import com.dotin.dotintasktwo.service.CategoryService;
import com.dotin.dotintasktwo.service.EmployeeService;
import com.dotin.dotintasktwo.utility.Time;
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


    private final CategoryService categoryService;
    private final CategoryElementService categoryElementService;
    private final EmployeeService employeeService;
    private boolean isEmployeeUpdate;


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

        ModelAndView modelAndView = new ModelAndView("employee/employees.jsp");
        List<Employee> employees = employeeService.findAll(pageable);
        int totalRecords = employeeService.findAll().size();

        modelAndView.addObject("employees", employees);
        modelAndView.addObject("totalRecords", totalRecords);

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {

        ModelAndView modelAndView = new ModelAndView("employee/addEmployee.jsp");

        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        Time time = new Time();
        theEmployee.setVersion(1);
        theEmployee.setCreateDate(time.getTime());
        List<Category> categories = categoryService.getAllCategory();
        if (categoryService.getAllCategory().stream().noneMatch(
                i -> i.getCategoryName().equals("userRole"))) {

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
            categoryElement1.setCategory(category1);

            categoryElement2.setName("تستر");
            categoryElement2.setCode("TESTER");
            categoryElement2.setCategory(category1);


            categoryElement3.setName("غیره");
            categoryElement3.setCode("OTHER");
            categoryElement3.setCategory(category1);

            categoryElement4.setName("مدیر برنامه نویس");
            categoryElement4.setCode("PROGRAMMER_MANGER");
            categoryElement4.setCategory(category1);

            categoryElement5.setName("مدیر تستر");
            categoryElement5.setCode("TESTER_MANAGER");
            categoryElement5.setCategory(category1);

            categoryElement6.setName("مدیر غیره");
            categoryElement6.setCode("OTHER_manager");
            categoryElement6.setCategory(category1);

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
        modelAndView.addObject("managers", employeeService.findManager());


        return modelAndView;
    }

    @RequestMapping("/showFormForUpdate/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") long theId) {

        ModelAndView modelAndView = new ModelAndView("employee/addEmployee.jsp");

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        List<Employee> managers = employeeService.findAll();
        isEmployeeUpdate = true;

        modelAndView.addObject("employee", theEmployee);
        modelAndView.addObject("categories", categoryService.getAllCategory());
        modelAndView.addObject("categoryElement", categoryElementService.getAllCategoryElements());
        modelAndView.addObject("managers", managers);

        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/employee/addEmployee.jsp");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/employees/list");

        if (isEmployeeUpdate) {
            Time time = new Time();
            theEmployee.setVersion(theEmployee.getVersion() + 1);
            theEmployee.setModifiedDate(time.getTime());
            isEmployeeUpdate = false;
            employeeService.Save(theEmployee);
            return modelAndView;
        }


        if (((employeeService.findAll().stream().anyMatch(i -> i.getEmail().equals(theEmployee.getEmail()))))) {
            ModelAndView modelAndView2 = new ModelAndView("/employee/addEmployee.jsp");
            modelAndView2.addObject("message", "این ایمیل تکراری می باشد");
            return modelAndView2;
        }

        employeeService.Save(theEmployee);
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
        ModelAndView modelAndView = new ModelAndView("employee/employees.jsp");


        // delete the employee
        List<Employee> theEmployees = employeeService.searchBy(theName);

        // add to the spring model
        modelAndView.addObject("employees", theEmployees);

        // send to /employees/list
        return modelAndView;

    }


}
