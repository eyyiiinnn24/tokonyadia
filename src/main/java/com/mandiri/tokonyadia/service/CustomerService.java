package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.entity.Customer;

import java.util.List;

public interface CustomerService {

        Customer saveCustomer(Customer customer);
        Customer updateCustomer(Customer customer);
        List<Customer> getAllCustomer();
        Customer getCustomerById(String CustomerId);
        void deleteCustomer(String id);
    }

