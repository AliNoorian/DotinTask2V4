package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {


    Leave findByLeaveId(long leaveId);


    void deleteLeave(long leaveId);

    void Save(Leave leave);

    List<Leave> findAll(Pageable pageable);

    Page<Leave> getLeaves(Employee employee, Pageable pageable);

    List<Leave> findAllApproved();

    List<Leave> findAllPending();

    void grantLeave(long leaveId);

    void rejectLeave(long leaveId);
}
