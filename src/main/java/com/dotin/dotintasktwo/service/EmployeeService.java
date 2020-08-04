package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployee(long id);

    void addEmployee(Employee employee);

    void removeEmployee(long id);

    List<Employee> searchBy(String theName);

}
