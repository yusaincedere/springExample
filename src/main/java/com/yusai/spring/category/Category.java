package com.yusai.spring.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yusai.spring.product.Product;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Set;

@Entity(name = "Category")
@Table(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )

    @Column(
            name = "category_id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Product> products = new ArrayList<>();

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category(String name) {
        this.name = name;
    }

}
