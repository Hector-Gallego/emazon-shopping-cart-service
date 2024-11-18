package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.math.BigDecimal;
import java.util.List;

public class SaleData {

    private List<ItemSaleData> itemsSaleData;
    private BigDecimal totalPurchase;

    public SaleData() {
    }

    public SaleData(List<ItemSaleData> itemsSaleData, BigDecimal totalPurchase) {
        this.itemsSaleData = itemsSaleData;
        this.totalPurchase = totalPurchase;
    }

    public List<ItemSaleData> getItemsSaleData() {
        return itemsSaleData;
    }

    public void setItemsSaleData(List<ItemSaleData> itemsSaleData) {
        this.itemsSaleData = itemsSaleData;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
}
