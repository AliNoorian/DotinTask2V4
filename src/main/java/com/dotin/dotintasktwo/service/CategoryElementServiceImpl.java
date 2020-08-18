package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.*;
import com.dotin.dotintasktwo.repository.CategoryElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class CategoryElementServiceImpl implements CategoryElementService {

    private final CategoryElementRepository categoryElementRepository;

    @Autowired
    public CategoryElementServiceImpl(CategoryElementRepository categoryElementRepository) {
        this.categoryElementRepository = categoryElementRepository;
    }

    @Override
    public List<CategoryElement> getAllCategoryElements() {
        return categoryElementRepository.findAll();
    }

    @Override
    public CategoryElement getCategoryElement(long Id) {
        Optional<CategoryElement> result = categoryElementRepository.findById(Id);

        CategoryElement categoryElement;

        if (result.isPresent()) {
            categoryElement = result.get();
        } else {
            // we didn't find the Category element
            throw new RuntimeException("Did not find category element id - " + Id);
        }

        return categoryElement;
    }

    @Override
    public void addCategoryElement(CategoryElement categoryElement) {
        categoryElementRepository.save(categoryElement);
    }



    @Override
    public void removeCategoryElement(long id) {
        categoryElementRepository.deleteById(id);
    }

    @Override
    public List<CategoryElement> getAllCategoryRoleElements() {
        return categoryElementRepository.findAllRoleCategory("userRole");
    }

    @Override
    public List<CategoryElement> getAllCategoryLeaveStatusElements() {
        return categoryElementRepository.findAllStatusCategory("leaveStatus");
    }

    @Override
    public CategoryElement getApprovedCategoryElement() {
        return categoryElementRepository.findByApproved("APPROVED");
    }

    @Override
    public CategoryElement getRejectedCategoryElement() {
        return categoryElementRepository.findByRejected("REJECTED");
    }

    @Override
    public CategoryElement getPendingCategoryElement() {
        return categoryElementRepository.findByPending("PENDING");
    }

    @Override
    public CategoryElement getCategoryElementByCode(String codeName) {
        return categoryElementRepository.findByCode(codeName);
    }

    @Override
    public List<CategoryElement> getCategoryName(Category getCategoryName) {
        return categoryElementRepository.findAllByCategory(getCategoryName);
    }


}
