package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface EmployeeService {

    void Save(Employee employee);

    void deleteEmployee(long empId);


    List<Employee> searchBy(String theName);

    Employee findById(long empId);

    List<Employee> sortByProperty(String property);

    List<Employee> findAll(Pageable pageable);

    List<Employee> findAll();

    void addLeave(String userName, Leave leave);

    List<Employee> findManager();


}
