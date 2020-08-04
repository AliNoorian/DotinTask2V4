package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
