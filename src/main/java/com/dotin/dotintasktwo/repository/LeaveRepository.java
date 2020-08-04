package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {


}
