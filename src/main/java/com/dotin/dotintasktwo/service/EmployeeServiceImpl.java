package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.repository.EmployeeRepository;
import com.dotin.dotintasktwo.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    //
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


//    @Override
//    public List<Employee> searchBy(String theName) {
//
//        List<Employee> results;
//
//        if (theName != null && (theName.trim().length() > 0)) {
//            results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
//        } else {
//            results = findAll();
//        }
//
//        return results;
//    }

    @Override
    public void Save(Employee employee) {

        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(long empId) {

        employeeRepository.deleteById(empId);
    }

    @Override
    public void updateEmployeeDetails(Employee employee) {

        Optional<Employee> result = employeeRepository.findById(employee.getId());

        Employee oldEmployee;

        if (result.isPresent()) {
            oldEmployee = result.get();
            oldEmployee.setActive(employee.getActive());
            oldEmployee.setVersion(employee.getVersion() + 1);
            oldEmployee.setEmployeeGender(employee.getEmployeeGender());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setEmployeeRole(employee.getEmployeeRole());
            employeeRepository.save(oldEmployee);


        } else {
            // we didn't find the employee
            throw new RuntimeException("این کاربر یافت نشد! " + employee);
        }

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


    //to generate dynamic query to find empployee according to given date and order by username (desc) order by id(ASC)
    @Override
    public List<Employee> getEmployeeBetweenDates(Date fromDate, Date toDate, String name, Pageable pageable) {

        return employeeRepository.findAll((Specification<Employee>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate p = criteriaBuilder.conjunction();
            if (Objects.nonNull(fromDate) && Objects.nonNull(toDate) && fromDate.before(toDate)) {

                p = criteriaBuilder.and(p, criteriaBuilder.between(root.get("dateOfJoining"), fromDate, toDate));
            }
            if (!StringUtils.isEmpty(name)) {

                p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("username"), "%" + name + "%"));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("username")), criteriaBuilder.asc(root.get("id")));
            return p;

        }, pageable).getContent();
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
        return employeeRepository.findManager();
    }


}







