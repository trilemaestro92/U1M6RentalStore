package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice deleteInvoice(int id);

    Invoice findInvoiceByCustomer(int id);

    List<Invoice> getAllInvoices();

    Invoice findInvoiceById (int id);

}
