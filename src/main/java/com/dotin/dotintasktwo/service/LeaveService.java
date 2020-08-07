package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Leave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {

    List<Leave> getAllLeaves();

    Leave getLeave(long id);

    void addLeave(Leave leave);

    void disableLeave(Leave leave);

    void removeLeave(long id);

    Leave findByLeaveId(long leaveId);

    void grantLeave(long leaveId);

    void rejectLeave(long leaveId);
}
