package com.yusai.spring.product;

import com.yusai.spring.category.Category;
import com.yusai.spring.category.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;



    @Autowired
    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll(){
        logger.info("Service Called: Get all products");
        List<Product> productList = productRepository.findAll();

        if(productList.size()>0){
            logger.info("Products found");
            return productList;
        }
        logger.info("There is no product");
        return productList;
    }

    public Optional<Product> getProductById(int id){
        logger.info("Service Called: Get product by id");
            return productRepository.findById(id);
    }

    public void addNewProduct(Product product) {
        logger.info("Service Called: Add new product");
        try {
            Category category = categoryRepository.getById(product.getCategoryId());
            if(categoryRepository.existsById(category.getId())){
                product.setCategory(category);
                productRepository.save(product);
                logger.info("Product added successfully");
            }else {
               logger.warn("There is no category with this category id");
            }
        }catch (Exception e){
           logger.error("Error happened while adding the product");
        }
    }

    public void deleteAllBYIds(ArrayList<Integer> ids) {
        logger.info("Service called: Delete products");
        List<Product> findAll = productRepository.findAllById(ids);
        Set<Integer> idList = new HashSet<>();
        for (Product product : findAll) {
            idList.add(product.getId());
        }
        if (!idList.isEmpty()) {
            productRepository.delete(idList);
            logger.info("Products deleted successfully");
        }else{
            logger.warn("There is no product with this id");
        }

    }

    public void updateProduct(Product newProduct, int id) {
        logger.info("Service called: Update product");
        Product product = productRepository.getById(id);
        Category category = categoryRepository.getById(newProduct.getCategoryId());

        if(categoryRepository.existsById(category.getId()) && productRepository.existsById(product.getId())){
            product.setCategoryId(newProduct.getCategoryId());
            product.setDetails(newProduct.getDetails());
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setCategory(category);
            productRepository.flush();
            logger.info("Update finished");
        }else {
            logger.warn("Could not find product or category");
        }
    }
}
