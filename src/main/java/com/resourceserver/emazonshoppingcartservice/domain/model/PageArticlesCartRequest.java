package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.util.List;

public class PageArticlesCartRequest {

    List<CartItem> articlesCart;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortOrder;
    private String categoryNameFilter;
    private String brandNameFilter;

    public PageArticlesCartRequest() {
    }

    public PageArticlesCartRequest(List<CartItem> articlesCart, Integer pageNumber, Integer pageSize, String sortOrder, String categoryNameFilter, String brandNameFilter) {
        this.articlesCart = articlesCart;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
        this.categoryNameFilter = categoryNameFilter;
        this.brandNameFilter = brandNameFilter;
    }

    public List<CartItem> getArticlesCart() {
        return articlesCart;
    }

    public void setArticlesCart(List<CartItem> articlesCart) {
        this.articlesCart = articlesCart;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCategoryNameFilter() {
        return categoryNameFilter;
    }

    public void setCategoryNameFilter(String categoryNameFilter) {
        this.categoryNameFilter = categoryNameFilter;
    }

    public String getBrandNameFilter() {
        return brandNameFilter;
    }

    public void setBrandNameFilter(String brandNameFilter) {
        this.brandNameFilter = brandNameFilter;
    }
}

