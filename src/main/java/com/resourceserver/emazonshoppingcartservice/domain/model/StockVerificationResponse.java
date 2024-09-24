package com.resourceserver.emazonshoppingcartservice.domain.model;

import java.time.LocalDate;


public class StockVerificationResponse {

    private Boolean isSufficientStockAvailable;
    private Boolean isCategoryLimitExceeded;
    private LocalDate nextRestockDate;

    public StockVerificationResponse() {
    }

    public StockVerificationResponse(Boolean isSufficientStockAvailable, Boolean isCategoryLimitExceeded, LocalDate nextRestockDate) {
        this.isSufficientStockAvailable = isSufficientStockAvailable;
        this.isCategoryLimitExceeded = isCategoryLimitExceeded;
        this.nextRestockDate = nextRestockDate;
    }

    public LocalDate getNextRestockDate() {
        return nextRestockDate;
    }

    public void setNextRestockDate(LocalDate nextRestockDate) {
        this.nextRestockDate = nextRestockDate;
    }

    public Boolean getCategoryLimitExceeded() {
        return isCategoryLimitExceeded;
    }

    public void setCategoryLimitExceeded(Boolean categoryLimitExceeded) {
        isCategoryLimitExceeded = categoryLimitExceeded;
    }

    public Boolean getSufficientStockAvailable() {
        return isSufficientStockAvailable;
    }

    public void setSufficientStockAvailable(Boolean sufficientStockAvailable) {
        isSufficientStockAvailable = sufficientStockAvailable;
    }
}
