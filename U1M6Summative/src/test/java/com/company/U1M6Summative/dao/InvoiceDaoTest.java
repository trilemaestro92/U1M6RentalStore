package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

//    @Autowired
//    CustomerDao customerDao;
//    @Autowired
//    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        invoiceList.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoice_id()));
    }

    @Test
    public void addInvoice() {
        Invoice invoice = invoiceDao.addInvoice(generateTestInvoice1());
        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(invoiceList.size(), 1);
    }

    @Test
    public void deleteInvoice() {
        Invoice invoice = invoiceDao.addInvoice(generateTestInvoice1());
        invoiceDao.deleteInvoice(invoice.getInvoice_id());
        Invoice invoiceDeleteTest = invoiceDao.findInvoiceById(invoice.getInvoice_id());

        assertNull(invoiceDeleteTest);
    }

    @Test
    public void findInvoiceByCustomer() {
        Invoice invoice = generateTestInvoice1();
        Invoice invoiceFindTest = invoiceDao.findInvoiceByCustomer(invoice.getCustomer_id());
        assertEquals(invoiceFindTest, invoice);
    }

    public Invoice generateTestInvoice1() {
        Invoice test = new Invoice();
        test.setCustomer_id(42);
        test.setOrder_date(LocalDate.of(2000,1,1));
        test.setPickup_date(LocalDate.of(1999,9,9));
        test.setReturn_date(LocalDate.of(1980,12,12));
        test.setLate_fee(new BigDecimal("9.99"));

        return test;
    }

    public Invoice generateTestInvoice2() {
        Invoice test = new Invoice();
        test.setCustomer_id(99);
        test.setOrder_date(LocalDate.of(1800,5,5));
        test.setPickup_date(LocalDate.of(1950,8,30));
        test.setReturn_date(LocalDate.of(1971,3,22));
        test.setLate_fee(new BigDecimal("9.99"));
        return test;
    }
}