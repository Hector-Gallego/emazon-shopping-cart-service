package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.util.HashSet;
import java.util.Set;

public class Article {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Set<Category> categories = new HashSet<>();
    private Brand brand;

    public Article() {
    }

    public Article(Long id, String name, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public Article(Long id, String name, String description, Integer quantity, Double price, Set<Category> categories, Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.brand = brand;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
