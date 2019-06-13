package com.company.U1M6Summative.model;


import java.util.Objects;

public class Customer {
    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String company;
    private String phone;

    public Customer() {
        super();
    }

    public Customer(String first_name, String last_name, String email, String company, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.company = company;
        this.phone = phone;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customer_id == customer.customer_id &&
                first_name.equals(customer.first_name) &&
                last_name.equals(customer.last_name) &&
                email.equals(customer.email) &&
                company.equals(customer.company) &&
                phone.equals(customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, first_name, last_name, email, company, phone);
    }

}
