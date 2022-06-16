package com.yusai.spring.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping
    public void create(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Long> deleteByIds(@RequestBody ArrayList<Integer> ids) {

        productService.deleteAllBYIds(ids);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(path = "update/{id}")
    public void update(@RequestBody Product product,@PathVariable int id){
        productService.updateProduct(product,id);
    }
}
