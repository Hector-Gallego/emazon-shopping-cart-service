package com.resourceserver.emazonshoppingcartservice.domain.model;

import java.util.List;

public class StockVerificationRequest {

    private Long articleId;
    private Integer quantity;
    private List<Long> cartArticlesIds;

    public StockVerificationRequest() {
    }

    public StockVerificationRequest(Long articleId, Integer quantity, List<Long> cartArticlesIds) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.cartArticlesIds = cartArticlesIds;
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

    public List<Long> getCartArticlesIds() {
        return cartArticlesIds;
    }

    public void setCartArticlesIds(List<Long> cartArticlesIds) {
        this.cartArticlesIds = cartArticlesIds;
    }
}
