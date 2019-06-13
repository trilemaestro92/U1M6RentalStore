package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice deleteInvoice(int id);

    Invoice findInvoiceByCustomer(int id);

}
