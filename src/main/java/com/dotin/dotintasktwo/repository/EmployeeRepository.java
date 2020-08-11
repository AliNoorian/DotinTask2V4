package com.dotin.dotintasktwo.repository;

import com.dotin.dotintasktwo.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(
            String name, String lName);

//    @Query("SELECT c FROM CategoryElement c WHERE c.code LIKE %'MANAGER'%")
//    List<Employee> getManagers();

    Employee findByFirstName(String empName);

}
