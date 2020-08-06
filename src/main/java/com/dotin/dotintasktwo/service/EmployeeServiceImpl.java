package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee getEmployee(long id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return theEmployee;
    }


    @Override
    public List<Employee> searchBy(String theName) {

        List<Employee> results;

        if (theName != null && (theName.trim().length() > 0)) {
            results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        } else {
            results = getAllEmployees();
        }

        return results;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findManager() {
        return employeeRepository.findManager();
    }

    @Override
    public void removeEmployee(long id) {
        employeeRepository.deleteById(id);
    }


}







