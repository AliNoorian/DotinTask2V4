package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.CategoryElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryElementRepository extends JpaRepository<CategoryElement, Long> {


}

