package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface CategoryElementRepository extends JpaRepository<CategoryElement, Long> {

    @Query("select e from Employee e where e.employeeRole =:categoryElement")
    Map<Long, CategoryElement> getEnumEmployeeRole(CategoryElement categoryElement);

    @Query("select l from Leave l where l.leaveStatus =:categoryElement")
    Map<Long, CategoryElement> getEnumLeaveStatus(CategoryElement categoryElement);

    @Query("select l from Leave l where l.leaveType =:categoryElement")
    Map<Long, CategoryElement> getEnumLeaveType(CategoryElement categoryElement);

}

