package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.repository.EmployeeRepository;
import com.dotin.dotintasktwo.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               LeaveRepository leaveRepository) {

        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
    }


    @Override
    public Employee findById(long id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("این کاربر یافت نشد!" + id);
        }

        return theEmployee;
    }



    @Override
    public void Save(Employee employee) {

        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(long empId) {

        employeeRepository.deleteById(empId);
    }



    @Override
    public List<Employee> searchBy(String theName) {

        List<Employee> results;

        if (theName != null && (theName.trim().length() > 0)) {
            results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        } else {
            results = findAll();
        }

        return results;
    }


    @Override
    public List<Employee> findAll(Pageable pageable) {

        int pageNo = pageable.getPageNumber();

        return employeeRepository.findAll(PageRequest.of(pageNo, 4, Sort.by("id").descending())).getContent();
//		Sort descOrderById = Sort.by("id").descending();
//		return employeeRepository.findAll(descOrderById);

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    //to sort by property
    @Override
    public List<Employee> sortByProperty(String property) {

        //Sort descSortOrder = Sort.by(property).descending();
        Sort sortOrder = Sort.by(property);
        //list.stream().forEach(System.out::println);
        return employeeRepository.findAll(sortOrder);

    }




    // Employees Requested Leave are stored
    @Override

    public void addLeave(String name, Leave leave) {

        Employee employee = employeeRepository.findByFirstName(name);
        leave.setEmployee(employee);
        leaveRepository.save(leave);

        List<Leave> empLeaves = employee.getLeaves();
        empLeaves.add(leave);
        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> findManager() {
        return employeeRepository.findAll();
    }


}







