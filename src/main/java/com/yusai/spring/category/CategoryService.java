package com.yusai.spring.category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    public void addNewCategory(Category category) {
        logger.info("Service Called: Add new category");
        try {
            categoryRepository.save(category);
            logger.info("Category  added");
        }catch (Exception e){
            logger.error("Error happened while adding the category");
        }
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
