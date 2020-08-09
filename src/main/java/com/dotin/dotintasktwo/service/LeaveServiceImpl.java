package com.dotin.dotintasktwo.service;


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
    public List<Leave> findAllLeaves(int status) {
        return leaveRepository.findAll(findAllLeavesByStatus(status));
    }

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
    //    List<Category> categories = categoryRepository.findAll();
    //    List<CategoryElement> categoryElements = categoryElementRepository.findAll();

        // leave.setLeaveStatus(categoryElements.forEach(););
        leaveRepository.save(leave);
    }

    @Override
    public void rejectLeave(long leaveId) {
        Leave leave = findByLeaveId(leaveId);
        //     leave.setLeaveStatus(2);
        leaveRepository.save(leave);
    }

    @Override
    public List<Leave> findAll() {
        return leaveRepository.findAll();
    }

    @Override
    public void deleteLeave(long leaveId) {
        leaveRepository.deleteById(leaveId);
    }


    public Specification<Leave> findAllLeavesByStatus(int status) {
        return new Specification<Leave>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                return cb.equal(root.get("status"), status);
            }

        };
    }


//    @Override
//    public List<Leave> getAllLeaves() {
//        return leaveRepository.findAll();
//    }
//
//    @Override
//    public Leave getLeave(long id) {
//        Optional<Leave> result = leaveRepository.findById(id);
//
//        Leave leave;
//
//        if (result.isPresent()) {
//            leave = result.get();
//        } else {
//            // we didn't find the leave
//            throw new RuntimeException("Did not find leave id - " + id);
//        }
//
//        return leave;
//    }
//
//    @Override
//    public void addLeave(Leave leave) {
//        leave.setActive(true);
//        leave.setCreateDate(new Date().toString());
//        leave.setVersion(1);
//        leaveRepository.save(leave);
//    }
//
//
//    @Override
//    public void disableLeave(Leave leave) {
//        leave.setActive(false);
//        leave.setModifiedDate(new Date().toString());
//        leave.setVersion((leave.getVersion()) + 1);
//    }
//
//    @Override
//    public void removeLeave(long id) {
//        leaveRepository.deleteById(id);
//    }
//
//
//
//
//    @Override
//    public void grantLeave(long leaveId) {
//        Leave leave = leaveRepository.getOne(leaveId);
//        leave.setLeaveStatus(CategoryElement.class.cast(1));
//        leaveRepository.save(leave);
//    }
//
//    @Override
//    public void rejectLeave(long leaveId) {
//
//        Leave leave = leaveRepository.getOne(leaveId);
//        leave.setLeaveStatus(CategoryElement.class.cast(2));
//        leaveRepository.save(leave);
//    }
//
//    public Specification<Leave> findAllLeavesByStatus(long status) {
//        return new Specification<Leave>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                return cb.equal(root.get("status"), status);
//            }
//
//        };
//    }
}



