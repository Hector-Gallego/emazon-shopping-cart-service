package com.resourceserver.emazonshoppingcartservice.domain.model;

public class
CartItem {

    private Long id;
    private Long articleId;
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Long id, Long articleId, Integer quantity) {
        this.id = id;
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
