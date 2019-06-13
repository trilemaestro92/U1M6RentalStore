package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

<<<<<<< HEAD
    @Autowired
    InvoiceItemDao invoiceItemDao;
=======
>>>>>>> e3086cac48fe47e33e68e089b088c9bea244d76f
    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ItemDao itemDao;


    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        for (InvoiceItem i : invoiceItemList) {
            invoiceItemDao.deleteInvoiceItem(i.getInvoiceItemId());
        }

    }

    @Test
    public void addGetDeleteInvoiceItem(){
        Item testItem = new Item("Computer","A test computer",new BigDecimal("10.00"));

        Invoice testInvoice = new Invoice(22, LocalDate.of(2019,12,19),
                LocalDate.of(2019, 12, 22), LocalDate.of(2020, 2, 19),
                new BigDecimal(30.00));

        testItem = itemDao.addItem(testItem);
        testInvoice = invoiceDao.addInvoice(testInvoice);

        InvoiceItem invoiceItem1 = new InvoiceItem
                (testInvoice.getInvoice_id(),
                testItem.getItem_id(),
                1,
                new BigDecimal(50.00), new BigDecimal(15.00));



        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);
        InvoiceItem invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem1.getInvoiceItemId());

        assertEquals(invoiceItem1, invoiceItem2);

        invoiceItemDao.deleteInvoiceItem(invoiceItem1.getInvoiceItemId());

        invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem1.getInvoiceItemId());

        assertNull(invoiceItem2);

    }


}