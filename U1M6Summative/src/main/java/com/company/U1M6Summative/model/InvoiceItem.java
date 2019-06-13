package com.company.U1M6Summative.model;

import java.util.Objects;

public class InvoiceItem {
    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    private double unitRate;
    private double discount;

    public InvoiceItem() {

    }

    public InvoiceItem(int invoiceId, int itemId, int quantity, double unitRate, double discount) {
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

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double unitRate) {
        this.unitRate = unitRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
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
                Double.compare(that.unitRate, unitRate) == 0 &&
                Double.compare(that.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemId, quantity, unitRate, discount);
    }
}