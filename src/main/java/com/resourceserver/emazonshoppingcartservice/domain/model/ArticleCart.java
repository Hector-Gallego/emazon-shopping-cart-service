package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.time.LocalDate;
import java.util.Set;

public class ArticleCart extends Article {


    private boolean isSufficientStock;
    private LocalDate supplyDate;

    public ArticleCart(){

    }
    public ArticleCart(boolean isSufficientStock, LocalDate supplyDate) {
        this.isSufficientStock = isSufficientStock;
        this.supplyDate = supplyDate;
    }

    public ArticleCart(Long id, String name, Set<Category> categories, boolean isSufficientStock, LocalDate supplyDate) {
        super(id, name, categories);
        this.isSufficientStock = isSufficientStock;
        this.supplyDate = supplyDate;
    }

    public ArticleCart(Long id, String name, String description, Integer quantity, Double price, Set<Category> categories, Brand brand, boolean isSufficientStock, LocalDate supplyDate) {
        super(id, name, description, quantity, price, categories, brand);
        this.isSufficientStock = isSufficientStock;
        this.supplyDate = supplyDate;
    }

    public boolean isSufficientStock() {
        return isSufficientStock;
    }

    public void setSufficientStock(boolean sufficientStock) {
        isSufficientStock = sufficientStock;
    }

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }
}
