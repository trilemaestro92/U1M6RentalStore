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
import java.util.ArrayList;
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
        Invoice invoice1 = invoiceDao.findInvoiceById(invoice.getInvoice_id());
        assertNull(invoice1);
    }


    @Test
    public void findInvoiceByCustomerName(){
        Customer testCustomer = new Customer("Luis", "Salmeron", "ls8salmeron@yahoo.com", "Bootcamp","678-907-0634");
        testCustomer = customerDao.addCustomer(testCustomer);

        Invoice firstInvoice = new Invoice(testCustomer.getCustomer_id(),
                LocalDate.of(2019,1,1),
                LocalDate.of(2019,2, 3),
                LocalDate.of(2020,2, 3),
                new BigDecimal("4.99")
        );

        Invoice secondInvoice = new Invoice(testCustomer.getCustomer_id(),
                LocalDate.of(2019,1,2),
                LocalDate.of(2019,3, 3),
                LocalDate.of(2020,3, 3),
                new BigDecimal("4.99")
        );
        invoiceDao.addInvoice(firstInvoice);
        invoiceDao.addInvoice(secondInvoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        assertEquals(invoiceList.size(), 2);


    }
}