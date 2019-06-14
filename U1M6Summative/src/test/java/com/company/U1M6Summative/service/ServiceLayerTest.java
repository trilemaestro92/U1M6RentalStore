package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewModel.InvoiceItemViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class ServiceLayerTest {

    ServiceLayer service;
    InvoiceItemDao invoiceItemDao;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceItemDaoMock();
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpItemDaoMock();
        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }

    @Test
    public void saveInvoiceItem() {
            InvoiceItemViewModel iivm = new InvoiceItemViewModel();

            iivm.setQuantity(2);
            iivm.setUnitRate(new BigDecimal("2.58"));
            iivm.setDiscount(new BigDecimal("5.32"));

            Customer customer = new Customer();
            customer.setFirst_name("John");
            customer.setLast_name("Tansor");
            customer.setCompany("TansorCo");
            customer.setEmail("jTansor2019@g.com");
            customer.setPhone("7738902345");
            customer = customerDao.addCustomer(customer);


            Invoice invoice = new Invoice();
            invoice.setOrder_date(LocalDate.of(2019, 06, 13));
            invoice.setPickup_date(LocalDate.of(2019, 06, 14));
            invoice.setLate_fee(new BigDecimal("1.02"));
            invoice.setReturn_date(LocalDate.of(2019, 07, 14));
            invoice.setCustomer_id(customer.getCustomer_id());
//        invoice.setInvoice_id(invoice);
            invoice = service.saveInvoice(invoice);

            iivm.setInvoice(invoice);

            Item item = new Item();
            item.setName("Candy");
            item.setDescription("This is chocholate candy bar.");
            item.setDaily_rate(new BigDecimal("0.36"));
            item = service.saveItem(item);

            iivm.setItem(item);

            iivm = service.saveInvoiceItem(iivm);
            InvoiceItemViewModel fromService = service.findInvoice(14);
            assertEquals(iivm, fromService);


        }
        /*
    Set up mock
     */
        private void setUpInvoiceItemDaoMock() {

        }
        private void setUpCustomerDaoMock() {
            customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
            Customer customer = new Customer();
            customer.setFirst_name("John");
            customer.setLast_name("Tansor");
            customer.setCompany("TansorCo");
            customer.setEmail("jTansor2019@g.com");
            customer.setPhone("7738902345");
            customer.setCustomer_id(7);

            Customer customer2 = new Customer();
            customer2.setFirst_name("John");
            customer2.setLast_name("Tansor");
            customer2.setCompany("TansorCo");
            customer2.setEmail("jTansor2019@g.com");
            customer2.setPhone("7738902345");

            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);

            doReturn(customer).when(customerDao).addCustomer(customer2);
            doReturn(customer).when(customerDao).getCustomer(7);
            doReturn(customerList).when(customerDao).getAllCustomers();

        }
        private void setUpInvoiceDaoMock() {
         invoiceDao= mock(InvoiceDaoJdbcTemplateImpl.class);
         Invoice invoice =  new Invoice();
         invoice.setInvoice_id(5);
         invoice.setReturn_date(LocalDate.of(2019,05, 21));
        invoice.setLate_fee(new BigDecimal("0.25"));
        invoice.setPickup_date(LocalDate.of(2019,05, 22));
        }
        private void setUpItemDaoMock() {
            itemDao = mock(ItemDaoJdbcTemplateImpl.class);
            Item item = new Item("Bike","Bike rental", new BigDecimal("2.99"));
            item.setItem_id(4);
            Item item2 = new Item("Bike","Bike rental", new BigDecimal("2.99"));
            List<Item> itemList = new ArrayList<>();
            itemList.add(item);
            itemList.add(item2);
            doReturn(item).when(itemDao).addItem(item2);
            doReturn(item).when(itemDao).getItem(4);
            doReturn(itemList).when(itemDao).getAllItem();
        }



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