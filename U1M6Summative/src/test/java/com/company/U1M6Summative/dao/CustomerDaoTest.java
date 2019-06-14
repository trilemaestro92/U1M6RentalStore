package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Customer> allCustomers = customerDao.getAllCustomers();
        allCustomers.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));
    }

    @Test
    public void addGetDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customer =customerDao.addCustomer(customer);
        Customer customer1 = customerDao.getCustomer(customer.getCustomer_id());
        assertEquals(customer1, customer);
        customerDao.deleteCustomer((customer.getCustomer_id()));
        customer1=customerDao.getCustomer(customer.getCustomer_id());
        assertNull(customer1);
    }


    @Test
    public void getAllCustomers() {
        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customerDao.addCustomer(customer);
        List<Customer> customerList = customerDao.getAllCustomers();
        assertEquals(customerList.size(), 1);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirst_name("Ellen");
        customer.setLast_name("Martin");
        customer.setCompany("EllenCo");
        customer.setEmail("ellen12@gmail.com");
        customer.setPhone("3122345678");
        customerDao.addCustomer(customer);

        customer.setFirst_name("Marta");
        customer.setLast_name("Martin");
        customer.setCompany("MartaCo");
        customer.setEmail("marta12@gmail.com");
        customer.setPhone("3122235678");
        customerDao.updateCustomer(customer);
        Customer customer1 = customerDao.getCustomer(customer.getCustomer_id());
        assertEquals(customer1, customer);

    }

}