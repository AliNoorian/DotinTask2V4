package com.dotin.dotintasktwo.service;


import com.dotin.dotintasktwo.model.Category;
import com.dotin.dotintasktwo.model.CategoryElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface CategoryElementService {

    List<CategoryElement> getAllCategoryElements();

    CategoryElement getCategoryElement(long id);

    void addCategoryElement(CategoryElement categoryElement);

    void removeCategoryElement(long id);

    List<CategoryElement> getAllCategoryRoleElements();

    List<CategoryElement> getAllCategoryLeaveStatusElements();

    CategoryElement getApprovedCategoryElement();
    CategoryElement getRejectedCategoryElement();
    CategoryElement getPendingCategoryElement();

    CategoryElement getCategoryElementByCode(String codeName);
}
