package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    void deleteInvoice(int id);

    List<Invoice> findInvoiceByCustomer(String first, String last);

    List<Invoice> getAllInvoices();

    Invoice findInvoiceById (int id);

}
