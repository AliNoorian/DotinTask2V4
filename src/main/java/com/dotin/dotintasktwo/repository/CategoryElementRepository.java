package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.CategoryElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryElementRepository extends JpaRepository<CategoryElement, Long> {

    @Query("select ce from CategoryElement ce where ce.category.categoryName like %:userRole%  and ce.active = true")
    List<CategoryElement> findAllRoleCategory(@Param("userRole") String userRole);

    @Query("select ce from CategoryElement ce where ce.category.categoryName like %:leaveStatus%  and ce.active = true")
    List<CategoryElement> findAllStatusCategory(@Param("leaveStatus") String leaveStatus);

    @Query("select ce from CategoryElement ce where ce.code like %:APPROVED%  and ce.active = true")
    CategoryElement findByApproved(@Param("APPROVED") String APPROVED);

    @Query("select ce from CategoryElement ce where ce.code like %:REJECTED%  and ce.active = true")
    CategoryElement findByRejected(@Param("REJECTED") String REJECTED);

    @Query("select ce from CategoryElement ce where ce.code like %:PENDING%  and ce.active = true")
    CategoryElement findByPending(@Param("PENDING") String PENDING);

}

