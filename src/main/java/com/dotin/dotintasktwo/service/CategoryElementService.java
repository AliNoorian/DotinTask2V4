package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.CategoryElement;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryElementService {

    List<CategoryElement> getAllCategoryElements();

    CategoryElement getCategoryElement(long id);

    void addCategoryElement(CategoryElement categoryElement);

    void removeCategoryElement(long id);


}
