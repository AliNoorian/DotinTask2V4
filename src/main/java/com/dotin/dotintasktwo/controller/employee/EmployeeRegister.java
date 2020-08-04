//package com.dotin.dotintasktwo.controller.employee;
//
//
//import com.dotin.dotintasktwo.model.Employee;
//import com.dotin.dotintasktwo.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//@Controller
//public class EmployeeRegister {
//
//    private Employee employee;
//
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Autowired
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("employee",employee);
//        return "add/employee";
//    }
//
//    @PostMapping("/register")
//    public String registerSubmit(@ModelAttribute @Valid Employee employee,
//                                 BindingResult bindingResult,
//                                 Model model,
//                                 HttpSession httpSession){
//        return getString(employee, bindingResult, model, httpSession, employeeRepository);
//    }
//
//    public static String getString(@ModelAttribute @Valid Employee employee,
//                                   BindingResult bindingResult,
//                                   Model model, HttpSession httpSession,
//                                   EmployeeRepository employeeRepository) {
//        if (bindingResult.hasErrors()){
//            return "add/employee";
//        }
//        if (employeeRepository.findById(employee.getId()).isPresent()){
//            model.addAttribute("emailStatus","duplicate");
//            return "add/employee";
//        }
//        httpSession.setAttribute("Id",employee.getEmail());
//        httpSession.setMaxInactiveInterval(24 * 60 * 60);
//        employeeRepository.save(employee);
//        return "redirect:/index";
//    }
//}
