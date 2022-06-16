package com.yusai.spring.category;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        logger.info("Searching categories");
        return categoryService.getAll();
    }
    @PostMapping
    public void createCategory(@RequestBody Category category){
        logger.info("Adding the category");
        categoryService.addNewCategory(category);
    }


    @DeleteMapping("/id/{id}")
    public void deleteByIds(@PathVariable("id") int id) {
        logger.info("Deleting the product");
        categoryService.deleteCategory(id);
    }
}

