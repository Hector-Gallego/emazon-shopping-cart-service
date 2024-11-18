package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.math.BigDecimal;

public class PageArticlesCartResponse<T>{

    private CustomPage<T> customPage;
    private BigDecimal totalPurchase;

    public PageArticlesCartResponse() {
    }

    public PageArticlesCartResponse(CustomPage<T> customPage, BigDecimal totalPurchase) {
        this.customPage = customPage;
        this.totalPurchase = totalPurchase;
    }

    public CustomPage<T> getCustomPage() {
        return customPage;
    }

    public void setCustomPage(CustomPage<T> customPage) {
        this.customPage = customPage;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
}
