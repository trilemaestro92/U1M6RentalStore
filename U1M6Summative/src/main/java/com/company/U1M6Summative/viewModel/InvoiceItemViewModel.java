package com.company.U1M6Summative.viewModel;

import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.Item;

import java.util.Objects;

public class InvoiceItemViewModel {

    private int invoiceItemId;
    private Invoice invoiceId;
    private Item itemId;
    private int quantity;
    private double unitRate;
    private double discount;

    public InvoiceItemViewModel(){
        super();
    }
    public InvoiceItemViewModel(Invoice invoiceId, Item itemId, int quantity, double unitRate, double discount) {
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

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
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
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return invoiceItemId == that.invoiceItemId &&
                quantity == that.quantity &&
                Double.compare(that.unitRate, unitRate) == 0 &&
                Double.compare(that.discount, discount) == 0 &&
                invoiceId.equals(that.invoiceId) &&
                itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemId, quantity, unitRate, discount);
    }
}
