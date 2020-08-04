package com.dotin.dotintasktwo.service;

import com.dotin.dotintasktwo.model.CategoryElement;
import com.dotin.dotintasktwo.repository.CategoryElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


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


}
