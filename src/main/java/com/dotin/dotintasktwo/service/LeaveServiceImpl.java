package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public Leave getLeave(long id) {
        Optional<Leave> result = leaveRepository.findById(id);

        Leave leave;

        if (result.isPresent()) {
            leave = result.get();
        } else {
            // we didn't find the leave
            throw new RuntimeException("Did not find leave id - " + id);
        }

        return leave;
    }

    @Override
    public void addLeave(Leave leave) {
        leave.setActive(true);
        leave.setCreateDate(new Date().toString());
        leave.setVersion(1);
        leaveRepository.save(leave);
    }



    @Override
    public void disableLeave(Leave leave) {
        leave.setActive(false);
        leave.setModifiedDate(new Date().toString());
        leave.setVersion((leave.getVersion()) + 1);
    }

    @Override
    public void removeLeave(long id) {
        leaveRepository.deleteById(id);
    }


}
