package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryService {


    List<Category> getAllCategory();

    Category getCategory(long id);

    void addCategory(Category category);

    void removeCategory(long id);
}
