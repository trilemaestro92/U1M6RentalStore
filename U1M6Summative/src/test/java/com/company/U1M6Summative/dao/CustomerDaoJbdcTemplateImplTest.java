package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoJbdcTemplateImplTest {
    @Autowired

    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Customer> allCustomers = customerDao.getAllCustomers();
        allCustomers.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));
    }

    @Test
    public void addCustomer() {
    }

    @Test
    public void getCustomer() {
    }

    @Test
    public void getAllCustomers() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }
}