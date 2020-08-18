package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>, JpaSpecificationExecutor<Leave> {



    @Query("SELECT l FROM Leave l WHERE l.employee =:employee")
    Page<Leave> getLeaveByEmployeeEquals(@Param("employee") Employee employee, Pageable pageable);

    @Query("select l from Leave l where l.leaveStatus.code like %:APPROVED%  and l.active = true")
    List<Leave> findAllApprovedList(@Param("APPROVED") String approved);

    @Query("select l from Leave l where l.leaveStatus.code like %:PENDING%  and l.active = true")
    List<Leave> findAllPendingList(@Param("PENDING") String pending);

    @Query("select l from Leave l where l.leaveStatus.code like %:PENDING%  and l.active = true")
    Page<Leave> getLeaveByCodeEquals(@Param("PENDING") String pending, Pageable pageable);

    Page<Leave> findAllByLeaveStatus(CategoryElement leaveStatusCode, Pageable pageable);

    List<Leave> findAllByLeaveStatus(CategoryElement leaveStatus);

}


