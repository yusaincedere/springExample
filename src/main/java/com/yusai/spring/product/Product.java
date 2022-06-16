package com.yusai.spring.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yusai.spring.category.Category;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;
    @Column(
            name = "category_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer categoryId;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "Decimal(10,2)"

    )
    private Double price;
    @Column(
            name = "details",
            columnDefinition = "TEXT"
    )
    private String details;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    @JsonBackReference
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Product(String name, Double price, String details,int categoryId) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.details = details;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", category=" + category +
                '}';
    }
}
