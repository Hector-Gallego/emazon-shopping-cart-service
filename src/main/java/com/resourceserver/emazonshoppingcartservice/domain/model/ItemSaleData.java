package com.resourceserver.emazonshoppingcartservice.domain.model;


public class ItemSaleData {

    private Long articleId;
    private String articleName;
    private Integer articleQuantity;
    private String articleDescription;
    private Double articlePrice;

    public ItemSaleData() {
    }

    public ItemSaleData(Long articleId, String articleName, Integer articleQuantity, String articleDescription, Double articlePrice) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleQuantity = articleQuantity;
        this.articleDescription = articleDescription;
        this.articlePrice = articlePrice;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(Integer articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public Double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
    }
}