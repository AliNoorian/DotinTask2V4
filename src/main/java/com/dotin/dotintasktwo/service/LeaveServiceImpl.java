package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Employee;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {


    private final LeaveRepository leaveRepository;
    private final CategoryElementService categoryElementService;


    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository,
                            CategoryElementService categoryElementService) {
        this.leaveRepository = leaveRepository;
        this.categoryElementService = categoryElementService;

    }

//    @Override
//    public List<Leave> findAllLeaves(int status) {
//        return leaveRepository.findAll(findAllLeavesByStatus(status));
//    }

    @Override
    public Leave findByLeaveId(long leaveId) {
        Optional<Leave> result = leaveRepository.findById(leaveId);

        Leave leave;

        if (result.isPresent()) {
            leave = result.get();
        } else {
            // we didn't find the leave
            throw new RuntimeException("این شناسه مرخصی یافت نشد! " + leaveId);
        }

        return leave;
    }

    @Override
    public void grantLeave(long leaveId) {
        Leave leave = findByLeaveId(leaveId);
        leave.setLeaveStatus(categoryElementService.getApprovedCategoryElement());
        leaveRepository.save(leave);
    }

    @Override
    public void rejectLeave(long leaveId) {
        Leave leave = findByLeaveId(leaveId);
        leave.setLeaveStatus(categoryElementService.getRejectedCategoryElement());
        leaveRepository.save(leave);
    }



    @Override
    public void deleteLeave(long leaveId) {
        leaveRepository.deleteById(leaveId);
    }

    @Override
    public void Save(Leave leave) {
        leaveRepository.save(leave);
    }

    @Override
    public List<Leave> findAll(Pageable pageable) {

        int pageNo = pageable.getPageNumber();
        return leaveRepository.findAll(PageRequest.of(pageNo, 4, Sort.by("id").descending())).getContent();

    }

    @Override
    public Page<Leave> getLeaves(Employee employee, Pageable pageable) {
        return leaveRepository.getLeaveByEmployeeEquals(employee, pageable);
    }

    @Override
    public List<Leave> findAllApproved() {
        return leaveRepository.findAllApprovedList("APPROVED");
    }

    @Override
    public List<Leave> findAllPending() {
        return leaveRepository.findAllPendingList("PENDING");
    }


}