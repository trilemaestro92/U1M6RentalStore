package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer (Customer customer);
    Customer getCustomer(int id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int id);

}
