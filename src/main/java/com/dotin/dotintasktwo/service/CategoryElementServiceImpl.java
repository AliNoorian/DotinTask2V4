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
    public Map<Long, CategoryElement> getEnumEmployeeRole(Category category) {
        Map<Long, CategoryElement> categoryElementMap = new HashMap<>();
        EmployeeRole[] roles = EmployeeRole.values();

        for (EmployeeRole name : roles) {

            CategoryElement categoryElement = new CategoryElement();
            categoryElement.setEnglishTypeName(name.toString());
            categoryElement.setVersion(1);
            categoryElement.setActive(true);
            categoryElement.setCreateDate(new Date().toString());
            categoryElementMap.put(categoryElement.getId(), categoryElement);

        }

        return categoryElementMap;
    }

    @Override
    public Map<Long, CategoryElement> getEnumLeaveStatus(CategoryElement categoryelement) {
        Map<Long, CategoryElement> categoryElementMap = new HashMap<>();
        LeaveStatus[] leaveStatuses = LeaveStatus.values();

        for (LeaveStatus name : leaveStatuses) {

            CategoryElement categoryElement = new CategoryElement();
            categoryElement.setEnglishTypeName(name.toString());
            categoryElement.setVersion(1);
            categoryElement.setActive(true);
            categoryElement.setCreateDate(new Date().toString());
            categoryElementMap.put(categoryElement.getId(), categoryElement);

        }

        return categoryElementMap;
    }

    @Override
    public Map<Long, CategoryElement> getEnumLeaveType(CategoryElement categoryelement) {
        Map<Long, CategoryElement> categoryElementMap = new HashMap<>();
        LeaveType[] leaveTypes = LeaveType.values();

        for (LeaveType name : leaveTypes) {

            CategoryElement categoryElement = new CategoryElement();
            categoryElement.setEnglishTypeName(name.toString());
            categoryElement.setVersion(1);
            categoryElement.setActive(true);
            categoryElement.setCreateDate(new Date().toString());
            categoryElementMap.put(categoryElement.getId(), categoryElement);

        }

        return categoryElementMap;
    }


}
