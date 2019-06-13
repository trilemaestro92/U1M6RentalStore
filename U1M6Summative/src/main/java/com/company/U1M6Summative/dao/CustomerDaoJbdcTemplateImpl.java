package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public class CustomerDaoJbdcTemplateImpl implements CustomerDao{
        private JdbcTemplate jdbcTemplate;
        private static final String INSERT_CUSTOMER_SQL =
                "insert into customer (first_name, last_name, email, company, phone) values (?, ? , ?, ?, ?)";
        private static final String SELECT_CUSTOMER_SQL =
                "select * from customer where customer_id = ?";
        private static final String SELECT_ALL_CUSTOMER_SQL =
                "select * from customer";
        private static final String UPDATE_CUSTOMER_SQL =
                "update customer set first_name = ?, last_name = ?, email = ?, company = ?, phone =? where customer_id = ?";
        private static final String DELETE_SQL =
                "delete from customer where customer_id =?";
        //customer_id int(11) not null auto_increment primary key,
//    first_name varchar(50) not null,
//    last_name varchar(50) not null,
//    email varchar(75) not null,
//    company varchar(50) not null,
//    phone varchar(50) not null
        @Override
        public Customer addCustomer(Customer customer) {
            return null;
        }

        @Override
        public Customer getCustomer(int id) {
            return null;
        }

        @Override
        public List<Customer> getAllCustomers() {
            return null;
        }

        @Override
        public void updateCustomer() {

        }

        @Override
        public void deleteCustomer(int id) {

        }



}
