package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.model.Leave;
import com.dotin.dotintasktwo.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public Leave findByLeaveId(long leaveId) {
        return leaveRepository.findByLeaveId(leaveId);
    }


    @Override
    public void grantLeave(long leaveId) {
        Leave leave = leaveRepository.findByLeaveId(leaveId);
        leave.setLeaveStatus(CategoryElement.class.cast(1));
        leaveRepository.save(leave);
    }

    @Override
    public void rejectLeave(long leaveId) {

        Leave leave = leaveRepository.findByLeaveId(leaveId);
        leave.setLeaveStatus(CategoryElement.class.cast(2));
        leaveRepository.save(leave);
    }

    public Specification<Leave> findAllLeavesByStatus(long status) {
        return new Specification<Leave>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                return cb.equal(root.get("status"), status);
            }

        };
    }
}



