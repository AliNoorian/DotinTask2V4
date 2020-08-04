//package com.dotin.dotintasktwo.controller.employee;
//
//import com.dotin.dotintasktwo.model.Employee;
//import com.dotin.dotintasktwo.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class EmployeeEdit {
//
//    private EmployeeRepository employeeRepository;
//    private Employee employee;
//
//    @Autowired
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    @Autowired
//    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @GetMapping("/edit")
//    public String editPage(HttpSession httpSession, Model model){
//        if (employeeRepository.findById((Long) httpSession.getAttribute("Id")).isPresent()){
//            employee = employeeRepository.findById((Long) httpSession.getAttribute("Id")).get();
//            model.addAttribute("employee",employee);
//            return "employees";
//        }
//        return "redirect:/index";
//
//    }
//
//    @PostMapping("/edit")
//    public String editSubmit(@ModelAttribute Employee employee,
//                             HttpSession httpSession,
//                             Model model){
//        return getString(employee, httpSession, model, employeeRepository);
//    }
//
//    public static String getString(@ModelAttribute Employee employee, HttpSession httpSession, Model model, EmployeeRepository employeeRepository) {
//        if (httpSession.getAttribute("Id").equals(employee.getId())){
//            employeeRepository.save(employee);
//        }
//        else {
//            if (employeeRepository.findById(employee.getId()).isPresent()){
//                model.addAttribute("emailStatus","duplicate");
//                return "edit";
//            }
//            else {
//                employeeRepository.deleteById((Long) httpSession.getAttribute("Id"));
//                employeeRepository.save(employee);
//                httpSession.setAttribute("Id",employee.getEmail());
//            }
//        }
//
//        return "redirect:/edit";
//    }
//}
