package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import org.junit.Before;
import org.junit.BeforeClass;
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
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        invoiceList.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoice_id()));

        List<Customer> customerList = customerDao.getAllCustomers();

        customerList.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));


    }

    @Test
    public void addInvoice() {
        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setOrder_date(LocalDate.of(2000,1,1));
        invoice.setPickup_date(LocalDate.of(1999,9,9));
        invoice.setReturn_date(LocalDate.of(1980,12,12));
        invoice.setLate_fee(new BigDecimal("9.99"));

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(invoiceList.size(), 1);
    }

    @Test
    public void deleteInvoice() {
        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setOrder_date(LocalDate.of(2000,1,1));
        invoice.setPickup_date(LocalDate.of(1999,9,9));
        invoice.setReturn_date(LocalDate.of(1980,12,12));
        invoice.setLate_fee(new BigDecimal("9.99"));

        invoice = invoiceDao.addInvoice(invoice);
        invoiceDao.deleteInvoice(invoice.getInvoice_id());
        Invoice invoiceDeleteTest = invoiceDao.findInvoiceByCustomer(customerDao.getCustomer(customer.getCustomer_id()).getCustomer_id());

        assertNull(invoiceDeleteTest);
    }

    @Test
    public void findInvoiceByCustomer() {
//        BigDecimal bd

        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setOrder_date(LocalDate.of(2000,1,1));
        invoice.setPickup_date(LocalDate.of(1999,9,9));
        invoice.setReturn_date(LocalDate.of(1980,12,12));
        invoice.setLate_fee(new BigDecimal("9.99"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoiceFindTest = invoiceDao.findInvoiceByCustomer(invoice.getCustomer_id());
        assertEquals(invoice, invoiceFindTest);
    }
}