package com.dotin.dotintasktwo.repository;


import com.dotin.dotintasktwo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.categoryName =:name")
    Map<Long, Category> getENUMCategoryName();

}
