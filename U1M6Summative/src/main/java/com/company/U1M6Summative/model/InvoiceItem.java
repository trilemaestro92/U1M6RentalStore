package com.company.U1M6Summative.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    private BigDecimal unitRate;
    private BigDecimal discount;

    public InvoiceItem() {

    }

    public InvoiceItem(int invoiceId, int itemId, int quantity, BigDecimal unitRate, BigDecimal discount) {
        this.invoiceId = invoiceId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitRate = unitRate;
        this.discount = discount;
    }



    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                itemId == that.itemId &&
                quantity == that.quantity &&
                unitRate.equals(that.unitRate) &&
                discount.equals(that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemId, quantity, unitRate, discount);
    }
}