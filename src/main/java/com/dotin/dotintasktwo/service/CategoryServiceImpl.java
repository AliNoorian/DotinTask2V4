package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryName;
import com.dotin.dotintasktwo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long id) {
        Optional<Category> result = categoryRepository.findById(id);

        Category category;

        if (result.isPresent()) {
            category = result.get();
        } else {
            // we didn't find the category
            throw new RuntimeException("Did not find category id - " + id);
        }

        return category;
    }


    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void removeCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Map<Long, Category> getENUMCategoryName() {

        Map<Long, Category> categoryMap = new HashMap<>();
        CategoryName[] categoryENUM = CategoryName.values();

        for (CategoryName name : categoryENUM) {

            Category category = new Category();
            category.setCategoryName(name.toString());
            category.setVersion(1);
            category.setActive(true);
            category.setCreateDate(new Date().toString());
            categoryMap.put(category.getId(), category);

        }

        return categoryMap;
    }
}
