package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Leave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {

    List<Leave> findAllLeaves(int status);

    Leave findByLeaveId(long leaveId);

    void grantLeave(long leaveId);

    void rejectLeave(long leaveId);

    List<Leave> findAll();

    void deleteLeave(long leaveId);


}
