package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceItemDaoMock();
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpItemDaoMock();
        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }

    @After
    public void tearDown() throws Exception {
    }
    /*
    Mock test
     */
    @Test
    public void saveInvoiceItem() {
    }

    @Test
    public void findInvoiceItem() {
    }
    @Test
    public void saveFindFindAllItems() {
        Item item = new Item("Bike","Bike rental", new BigDecimal("2.99"));
        item = service.saveItem(item);
        Item item2 = new Item("Book","Book rental", new BigDecimal("2.99"));
        service.saveItem(item2);
        Item fromService = service.findItem(item.getItem_id());
        assertEquals(item, fromService);


        List<Item> itemList = service.findAllItems();
        assertEquals(2, itemList.size());
    }
    @Test
    public void updateItem(){

        Item item = new Item(4,"Bike", "Bike rental", new BigDecimal("2.99"));
        Item actual = service.updateItem(item);

        Item expecting = new Item(4,"Bike", "Bike rental", new BigDecimal("2.99"));
        assertEquals(expecting, actual);


    }
    @Test
    public void removeItem(){
        int deleteResult = service.removeItem(8);
        assertEquals(deleteResult, 1);
    }

    /*
    Set up mock
     */
    private void setUpInvoiceItemDaoMock() {};

    private void setUpCustomerDaoMock() {};

    private void setUpInvoiceDaoMock() {};

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item("Bike","Bike rental", new BigDecimal("2.99"));
        item.setItem_id(4);

        Item item2 = new Item("Book","Book rental", new BigDecimal("2.99"));
        item2.setItem_id(8);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(4);
        doReturn(item2).when(itemDao).getItem(8);
        doReturn(itemList).when(itemDao).getAllItem();
        doReturn(item).when(itemDao).updateItem(item);
    };
}