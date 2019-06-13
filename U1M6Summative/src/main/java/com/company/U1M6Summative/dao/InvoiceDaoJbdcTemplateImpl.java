package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvoiceDaoJbdcTemplateImpl implements InvoiceDao {

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice deleteInvoice(int id) {
        return null;
    }

    @Override
    public Invoice findInvoiceByCustomer(int id) {
        return null;
    }
}
