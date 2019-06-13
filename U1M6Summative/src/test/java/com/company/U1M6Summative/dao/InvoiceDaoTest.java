package com.company.U1M6Summative.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

    }

    @Test
    public void addInvoice() {
    }

    @Test
    public void deleteInvoice() {
    }

    @Test
    public void findInvoiceByCustomer() {
    }
}