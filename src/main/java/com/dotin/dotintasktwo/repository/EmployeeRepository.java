package com.dotin.dotintasktwo.repository;

import com.dotin.dotintasktwo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);

}
