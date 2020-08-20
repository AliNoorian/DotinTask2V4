package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public interface EmployeeService {

    void Save(Employee employee) throws InvocationTargetException, IllegalAccessException;

    void deleteEmployee(long empId);


    List<Employee> searchBy(String theName);

    Employee findById(long empId);

    List<Employee> sortByProperty(String property);

    List<Employee> findAll(Pageable pageable);

    List<Employee> findAll();

    List<Employee> findManager(String MANAGER);

    Employee findByName(String admin);
}
