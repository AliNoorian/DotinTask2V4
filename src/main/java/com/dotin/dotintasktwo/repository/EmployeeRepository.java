package com.dotin.dotintasktwo.repository;

import com.dotin.dotintasktwo.model.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(
            String name, String lName);

    @Query("select e from Employee e where e.employeeRole.code like :MANAGER%  and e.active = true")
    List<Employee> findManager(@Param("MANAGER") String manager);



    Employee findByFirstName(String empName);

}
