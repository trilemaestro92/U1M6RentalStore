package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewModel.InvoiceItemViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
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


//    @Test
//    public void saveInvoiceItem() {
//        InvoiceItemViewModel iivm = new InvoiceItemViewModel();
//
//        iivm.setQuantity(2);
//        iivm.setUnitRate(new BigDecimal("2.58"));
//        iivm.setDiscount(new BigDecimal("5.32"));
//
//        Customer customer = new Customer();
//        customer.setFirst_name("John");
//        customer.setLast_name("Tansor");
//        customer.setCompany("TansorCo");
//        customer.setEmail("jTansor2019@g.com");
//        customer.setPhone("7738902345");
//        customer = customerDao.addCustomer(customer);
//
//
//        Invoice invoice = new Invoice();
//        invoice.setOrder_date(LocalDate.of(2019, 06, 13));
//        invoice.setPickup_date(LocalDate.of(2019, 06, 14));
//        invoice.setLate_fee(new BigDecimal("1.02"));
//        invoice.setReturn_date(LocalDate.of(2019, 07, 14));
//        invoice.setCustomer_id(customer.getCustomer_id());
//        invoice.setInvoice_id(invoice);
//        invoice = service.saveInvoice(invoice);
//
//        iivm.setInvoice(invoice);
//
//        Item item = new Item();
//        item.setName("Candy");
//        item.setDescription("This is chocholate candy bar.");
//        item.setDaily_rate(new BigDecimal("0.36"));
//        item = service.saveItem(item);
//
//        iivm.setItem(item);
//
//        iivm = service.saveInvoiceItem(iivm);
//        InvoiceItemViewModel fromService = service.findInvoice(14);
//        assertEquals(iivm, fromService);
//
//    }

    // Invoice Item Tests
    @Test
    public void saveFindFindAllInvoiceItem() {
        InvoiceItemViewModel ivm = new InvoiceItemViewModel(50,
                new BigDecimal("2.99"), new BigDecimal("14.99"));

        Customer customer = new Customer();
        customer.setFirst_name("John");
        customer.setLast_name("Tansor");
        customer.setCompany("TansorCo");
        customer.setEmail("jTansor2019@g.com");
        customer.setPhone("7738902345");
        customer.setCustomer_id(7);

        Invoice invoice = new Invoice(
                5,
                customer.getCustomer_id(),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 22),
                new BigDecimal("0.25")
        );
        ivm.setInvoice(invoice);

        Item item = new Item(4,"Bike", "Bike rental", new BigDecimal("2.99"));
        ivm.setItem(item);
        ivm.setInvoiceItemId(18);

        ivm = service.saveInvoiceItem(ivm);

        InvoiceItemViewModel fromService = service.findInvoiceItem(ivm.getInvoiceItemId());

        assertEquals(ivm, fromService);

        List<InvoiceItemViewModel> ivmList = new ArrayList<>();
        ivmList.add(ivm);

        assertEquals(1, ivmList.size());

//        service.removeInvoice(5);
//        InvoiceItemViewModel ivm1 = service.findIVM(ivm.getInvoiceItemId());
//        Invoice checkInvoice = service.findInvoice(invoice.getInvoice_id());
//        assertNull(checkInvoice);

    }

    //Invoice Tests

    @Test
    public void saveFindFindAllInvoices() {
        Customer customer = new Customer(7, "John", "Tansor", "jTansor2019@g.com",
                "TansorCo", "7738902345");
        Invoice invoice = new Invoice(
                customer.getCustomer_id(),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 22),
                new BigDecimal("0.25")
        );
        invoice = service.saveInvoice(invoice);
        Invoice fromService = service.findInvoice(invoice.getInvoice_id());
        assertEquals(invoice, fromService);

        List<Invoice> invoiceList = service.findAllInvoices();
        assertEquals(1, invoiceList.size());
    }

    @Test
    public void removeInvoices() {
        int deleteResult = service.removeInvoice(5);
        assertEquals(deleteResult, 1);
    }

    // Customer Tests

    @Test
    public void saveFindFindAllCustomers() {
        Customer customer = new Customer("John", "Tansor", "jTansor2019@g.com", "TansorCo",
                "7738902345");
        customer = service.saveCustomer(customer);
        Customer fromService = service.findCustomer(customer.getCustomer_id());
        assertEquals(customer, fromService);

        List<Customer> customerList = service.findAllCustomers();
        assertEquals(1, customerList.size());
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer(7, "John", "Tansor", "jTansor2019@g.com", "TansorCo",
                "7738902345");
        Customer actual = service.updateCustomer(customer);

        Customer expecting = new Customer(7, "John", "Tansor", "jTansor2019@g.com", "TansorCo",
                "7738902345");
        assertEquals(expecting, actual);
    }

    @Test
    public void removeCustomer() {
        int deleteResult = service.removeCustomer(7);
        assertEquals(deleteResult, 1);
    }

    // Item Tests

    @Test
    public void saveFindFindAllItems() {

        Item item = new Item("Bike", "Bike rental", new BigDecimal("2.99"));
        item = service.saveItem(item);
        Item item2 = new Item("Book", "Book rental", new BigDecimal("2.99"));
        service.saveItem(item2);

        Item fromService = service.findItem(item.getItem_id());
        assertEquals(item, fromService);

        List<Item> itemList = service.findAllItems();
        assertEquals(2, itemList.size());
    }

    @Test
    public void updateItem() {

        Item item = new Item(4, "Bike", "Bike rental", new BigDecimal("2.99"));
        Item actual = service.updateItem(item);

        Item expecting = new Item(4, "Bike", "Bike rental", new BigDecimal("2.99"));
        assertEquals(expecting, actual);


    }

    @Test
    public void removeItem() {
        int deleteResult = service.removeItem(8);
        assertEquals(deleteResult, 1);
    }


    /*

    Set up mock

    */

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem(18, 5, 4, 50,
                new BigDecimal("2.99"), new BigDecimal("14.99"));

        InvoiceItem invoiceItem1 = new InvoiceItem(5, 4, 50,
                new BigDecimal("2.99"), new BigDecimal("14.99"));
        List<InvoiceItem> invoiceItemList = new ArrayList<>();

        invoiceItemList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(18);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItems();

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
        doReturn(customer).when(customerDao).updateCustomer(customer);

    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Customer customer = new Customer(7, "John", "Tansor", "jTansor2019@g.com",
                "TansorCo", "7738902345");

        Invoice invoice = new Invoice(
                5,
                customer.getCustomer_id(),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 22),
                new BigDecimal("0.25")
        );
        Invoice invoice2 = new Invoice(
                customer.getCustomer_id(),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 21),
                LocalDate.of(2019, 05, 22),
                new BigDecimal("0.25")
        );

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).findInvoiceById(5);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();

    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item(4, "Bike", "Bike rental", new BigDecimal("2.99"));
        Item itemA = new Item("Bike", "Bike rental", new BigDecimal("2.99"));

        Item item2 = new Item("Book", "Book rental", new BigDecimal("2.99"));
        item2.setItem_id(8);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);

        doReturn(item2).when(itemDao).addItem(itemA);
        doReturn(item).when(itemDao).getItem(4);
        doReturn(item2).when(itemDao).getItem(8);
        doReturn(itemList).when(itemDao).getAllItem();
        doReturn(item).when(itemDao).updateItem(item);
    }
}