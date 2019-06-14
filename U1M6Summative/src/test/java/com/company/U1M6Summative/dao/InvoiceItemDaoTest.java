package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
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

    @Autowired
    InvoiceItemDao invoiceItemDao;

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
        Customer testCustomer = new Customer("Luis", "Salmeron", "ls8salmeron@yahoo.com", "Bootcamp","678-907-0634");
        testCustomer = customerDao.addCustomer(testCustomer);

        Item testItem = new Item("Computer","A test computer",new BigDecimal("10.00"));

        Invoice testInvoice = new Invoice(testCustomer.getCustomer_id(), LocalDate.of(2019,12,19),
                LocalDate.of(2019, 12, 22), LocalDate.of(2020, 2, 19),
                new BigDecimal(30.00));


        testItem = itemDao.addItem(testItem);
        testInvoice = invoiceDao.addInvoice(testInvoice);

        InvoiceItem invoiceItem = new InvoiceItem(testInvoice.getInvoice_id(), testItem.getItem_id(), 1, new BigDecimal("0.50"), new BigDecimal("2.82"));

        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem1, invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertNull(invoiceItem1);

    }


}